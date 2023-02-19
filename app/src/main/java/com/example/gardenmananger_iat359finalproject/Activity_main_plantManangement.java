package com.example.gardenmananger_iat359finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

//bottom navigation reference: https://www.youtube.com/watch?v=OV25x3a55pk

public class Activity_main_plantManangement extends AppCompatActivity {

    BottomNavigationView nav_bottom;

    Frag_plantManange fragPlantManange=new Frag_plantManange();
    Frag_records fragRecords=new Frag_records();
    Frag_settings fragSettings=new Frag_settings();

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
                    case R.id.button_nav_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layout_main,fragSettings).commit();
                        return true;
                }
                return false;
            }
        });
    }

    public void gotoRecord(){
        Toast.makeText(this,"go to record",Toast.LENGTH_SHORT).show();
    }
}