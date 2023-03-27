package com.example.gardenmananger_iat359finalproject;

import static android.hardware.Sensor.TYPE_LIGHT;
import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Activity_temperature extends AppCompatActivity {

    public String interfaceColour;

    private LinearLayout dataLayout;
    private TextView temperature;
    private EditText enterLat, enterLng;
    private String lat, lng, result, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        networkConnection();

        dataLayout = findViewById(R.id.dataLayout);
        temperature = findViewById(R.id.currentTemp);
        enterLat = findViewById(R.id.enterLat);
        enterLng = findViewById(R.id.enterLng);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);

        if (interfaceColour != null) {
            dataLayout.setBackgroundColor(Color.parseColor(interfaceColour));
        }

    }

    public void currentTemperature(View view) {

        lat = enterLat.getText().toString();
        lng = enterLng.getText().toString();

        url = "http://api.geonames.org/findNearByWeatherJSON?lat=" + lat + "&lng=" + lng + "&username=demo";

        Thread myThread = new Thread(new GetWeatherThread());
        myThread.start();

        try {
            JSONObject jsonObject = new JSONObject(result);

            Toast.makeText(this, jsonObject.getString("weatherObservation"), Toast.LENGTH_SHORT).show();

            JSONObject weatherObservationItems = new JSONObject(jsonObject.getString("weatherObservation"));

            temperature.setText("TEMPERATURE: " + weatherObservationItems.getString("temperature"));
        } catch (Exception e) {
            Log.d("ReadWeatherJSONDataTask", e.getLocalizedMessage());
        }
    }

    private class GetWeatherThread implements Runnable {
        @Override
        public void run() {
            Exception exception = null;
            try{
                result = readJSONData(url);
            }catch(IOException e){
                exception = e;
            }
        }
    }

    public void networkConnection(){
//        get manager for network connection
        ConnectivityManager connectManager =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
//        check .isConnected()
        if(networkInfo != null && networkInfo.isConnected()){
            String networkInformation = networkInfo.toString();
            Toast.makeText(this, networkInformation, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "no network connection", Toast.LENGTH_SHORT).show();
        }
    }

    private String readJSONData(String webUrl) throws IOException {
        InputStream is = null;

        int len = 2500;

        URL url = new URL(webUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            int response = conn.getResponseCode();
            Log.d("tag", "The response is: " + response);
            is = conn.getInputStream();

            String contentAsString = readIt(is, len);
            return contentAsString;

        } finally {
            if (is != null) {
                is.close();
                conn.disconnect();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}