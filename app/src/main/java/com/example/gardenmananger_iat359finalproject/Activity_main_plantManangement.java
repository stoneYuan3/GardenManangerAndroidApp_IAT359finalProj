package com.example.gardenmananger_iat359finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

//bottom navigation reference: https://www.youtube.com/watch?v=OV25x3a55pk
//https://developer.android.com/guide/fragments

public class Activity_main_plantManangement extends AppCompatActivity {

    BottomNavigationView nav_bottom;

    Frag_plantManange fragPlantManange=new Frag_plantManange();
    Frag_records fragRecords=new Frag_records();
    Frag_tools fragTools=new Frag_tools();
    Frag_settings fragSettings=new Frag_settings();

    plantDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_bottom=findViewById(R.id.nav_bottom);

        //set layout to plant manangement layout by default
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragPlantManange).commit();

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

    public void gotoRecord(){
        Toast.makeText(this,"go to record",Toast.LENGTH_SHORT).show();
    }
}