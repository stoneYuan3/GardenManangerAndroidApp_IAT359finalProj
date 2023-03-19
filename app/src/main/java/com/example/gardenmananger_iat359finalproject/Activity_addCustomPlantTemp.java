package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
//    private Button
    plantDatabase database;
    MyHelper helper;

    private ArrayList list_customPlant = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_plant_temp);

        database = new plantDatabase(this);
        helper=new MyHelper(this);

        input_plantName=findViewById(R.id.input_plantName);
        input_sunlight=findViewById(R.id.input_sunlight);
        input_humid=findViewById(R.id.input_humid);
        input_temperature=findViewById(R.id.input_temperature);
        input_ph=findViewById(R.id.input_ph);

    }

    public void submit(View v) {
        String plantName = String.valueOf(input_plantName.getText());
        String sunlight = String.valueOf(input_sunlight.getText());
        String humid = String.valueOf(input_humid.getText());
        String temperature = String.valueOf(input_temperature.getText());
        String ph = String.valueOf(input_ph.getText());

        list_customPlant.add(plantName);
        list_customPlant.add(sunlight);
        list_customPlant.add(humid);
        list_customPlant.add(temperature);
        list_customPlant.add(ph);

        long id = database.insertUserCustomPreset(list_customPlant);
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
            Log.d("insert","confirm add info fail");
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            Log.d("insert","confirm add info successful");
            Intent intent=new Intent(this,Activity_addPlants.class);
            startActivity(intent);
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