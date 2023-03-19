package com.example.gardenmananger_iat359finalproject;

import static android.hardware.Sensor.TYPE_LIGHT;

import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity_lightSensor extends AppCompatActivity implements SensorEventListener {

    public String interfaceColour;

    private LinearLayout dataLayout;
    private SensorManager sensorMaster;
    private Sensor sensor_light;
    private TextView text_lightsenData,text_lightClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        text_lightsenData=findViewById(R.id.text_lightsenData);
        text_lightClass=findViewById(R.id.text_lightClass);
        dataLayout = findViewById(R.id.dataLayout);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);

        sensorMaster=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorMaster.getDefaultSensor(TYPE_LIGHT)!=null){
            sensor_light=sensorMaster.getDefaultSensor(TYPE_LIGHT);
        }

        if (interfaceColour != null) {
            dataLayout.setBackgroundColor(Color.parseColor(interfaceColour));
        }

    }

    protected void onResume(){
        super.onResume();
        sensorMaster.registerListener(this,sensor_light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        sensorMaster.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==TYPE_LIGHT){
            float[] lightValAll = event.values;
            for(int i=0;i<lightValAll.length;i++){
                float valEach=lightValAll[i];
                text_lightsenData.setText(valEach+" luminance");

                if(valEach<5000){
                    text_lightClass.setText("full shade");
                }
                else if(valEach>5000 && valEach<20000){
                    text_lightClass.setText("partial shade");
                }
                else if(valEach>20000 && valEach<30000){
                    text_lightClass.setText("full sun");
                }
                else{
                    text_lightClass.setText("Apocalypse");
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    public void back(){
//        getFragmentManager().popBackStack();
//    }

}