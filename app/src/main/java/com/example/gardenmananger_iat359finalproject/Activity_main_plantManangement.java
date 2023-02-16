package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Activity_main_plantManangement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoRecord(){
        Toast.makeText(this,"go to record",Toast.LENGTH_SHORT).show();
    }
}