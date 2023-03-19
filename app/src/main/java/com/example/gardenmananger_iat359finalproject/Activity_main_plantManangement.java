package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

//bottom navigation reference: https://www.youtube.com/watch?v=OV25x3a55pk
//https://developer.android.com/guide/fragments

public class Activity_main_plantManangement extends AppCompatActivity implements View.OnClickListener{
    BottomNavigationView nav_bottom;

    Frag_plantManange fragPlantManange=new Frag_plantManange();
    Frag_records fragRecords=new Frag_records();
    Frag_tools fragTools=new Frag_tools();
    Frag_settings fragSettings=new Frag_settings();

    plantDatabase database;
    MyHelper helper;

    public static final String DEFAULT = "#F1F1F1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_bottom=findViewById(R.id.nav_bottom);

        //set layout to plant manangement layout by default
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragPlantManange).commit();


            SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            if (preferences.getString("colourId", DEFAULT) != null) {
                fragSettings.interfaceColour = preferences.getString("colourId", DEFAULT);
                fragRecords.interfaceColour = preferences.getString("colourId", DEFAULT);
                fragPlantManange.interfaceColour = preferences.getString("colourId", DEFAULT);
                fragTools.interfaceColour = preferences.getString("colourId", DEFAULT);
                nav_bottom.setBackgroundColor(Color.parseColor(preferences.getString("colourId", DEFAULT)));
            }


        //listen to changes on icons in nav_bottom and set layout accordingly
        nav_bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.button_nav_plants:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragPlantManange).commit();
                        return true;
                    case R.id.button_nav_records:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragRecords).commit();
                        return true;
                    case R.id.button_nav_tools:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragTools).commit();
                        return true;
                    case R.id.button_nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragSettings).commit();
                        return true;
                }
                return false;
            }
        });

        database = new plantDatabase(this);
        helper=new MyHelper(this);
        ArrayList arr=database.preparePresetPlantData("preset");
        //this for loop is for testing purposes only
        for(int i=0;i<arr.size();i++){
            String[] plantDataEach= (String[]) arr.get(i);
            for(int j=0;j<plantDataEach.length;j++){
                Log.d("presetPlants",i+" "+plantDataEach[j]);
            }
        }
    }

    public void addDatabaseEntry (View view)
    {
//      NOTE adding an entry to the database. Needs editText of user input

//        String name = name.getText().toString();

//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
//        long id = database.insertData(name);
//        if (id < 0)
//        {
//            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
//        }

    }

    public void startToolActivity(View v){
        Intent intent=new Intent(this,Activity_lightSensor.class);
        startActivity(intent);
    }

    public void startAddPlantActivity(View v){
        Intent intent=new Intent(this,Activity_addPlants.class);
        startActivity(intent);
    }

    public void startAddRecordActivity(View v){
        Intent intent=new Intent(this, Activity_addRecord.class);
        startActivity(intent);
    }
    public void gotoRecord(){
        Toast.makeText(this,"go to record",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    public void prefs(View v) {
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("colourId", "#" + fragSettings.id);
        editor.commit();
//        Toast.makeText(this,preferences.getString("colourId", DEFAULT),Toast.LENGTH_SHORT).show();
        fragSettings.interfaceColour = preferences.getString("colourId", DEFAULT);
        fragRecords.interfaceColour = preferences.getString("colourId", DEFAULT);
        fragPlantManange.interfaceColour = preferences.getString("colourId", DEFAULT);
        fragTools.interfaceColour = preferences.getString("colourId", DEFAULT);

        fragSettings.settingsTextView.setBackgroundColor(Color.parseColor(fragSettings.interfaceColour));
        nav_bottom.setBackgroundColor(Color.parseColor(preferences.getString("colourId", DEFAULT)));
    }
}