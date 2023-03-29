package com.example.gardenmananger_iat359finalproject;

import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;

import java.util.ArrayList;

public class Activity_addCustomPlantTemp extends AppCompatActivity {


    private EditText input_plantName,input_sunlight,input_humid,input_temperature,input_ph;
    private Button confirm, cancel;
    public String interfaceColour;
    plantDatabase database;
    MyHelper helper;

    private ArrayList list_customPlant = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_plant_temp);

        database = new plantDatabase(this);
        helper=new MyHelper(this);
        confirm=findViewById(R.id.b_preset_confirm);
        cancel=findViewById(R.id.b_preset_cancel);

        input_plantName=findViewById(R.id.input_plantName);
        input_sunlight=findViewById(R.id.input_sunlight);
        input_humid=findViewById(R.id.input_humid);
        input_temperature=findViewById(R.id.input_temperature);
        input_ph=findViewById(R.id.input_ph);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);
        if (interfaceColour != null) {
            confirm.setBackgroundColor(Color.parseColor(interfaceColour));
            cancel.setBackgroundColor(Color.parseColor(interfaceColour));
        }
    }

//    when user presses submit, adds the newly defined plant to the list and into the database
    public void submit(View v) {
        String plantName = input_plantName.getText().toString();
        String sunlight = input_sunlight.getText().toString();
        String humid = input_humid.getText().toString();
        String temperature = input_temperature.getText().toString();
        String ph = input_ph.getText().toString();

        if (plantName.length() > 0) {
            ArrayList<String> list_customPlant = new ArrayList<>();
            list_customPlant.add(plantName);
            list_customPlant.add(sunlight);
            list_customPlant.add(humid);
            list_customPlant.add(temperature);
            list_customPlant.add(ph);

            long id = database.insertUserCustomPreset(list_customPlant);
            if (id < 0) {
                Toast.makeText(this, "Insert failed", Toast.LENGTH_SHORT).show();
                Log.d("insert","Confirm add info failed");
            } else {
                Toast.makeText(this, "Insert successful", Toast.LENGTH_SHORT).show();
                Log.d("insert","Confirm add info successful");
                Intent intent = new Intent(this, Activity_addPlants.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Please provide at least the name", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View v){
        finish();
    }

//    public void insertFromEditText(ArrayList inputList){
//        for(int i=0;i<inputList.size();i++){
//            if(inputList.get(i).equals("")){
//                Toast.makeText(this, "You havent complete the form!", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            else{
//            }
//        }
//    }

}