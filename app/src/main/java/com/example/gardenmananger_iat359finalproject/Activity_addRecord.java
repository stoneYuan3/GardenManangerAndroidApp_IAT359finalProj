package com.example.gardenmananger_iat359finalproject;

import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_addRecord extends AppCompatActivity {

    public String interfaceColour;

    private TextView settingsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        settingsTextView = findViewById(R.id.settingsTextView);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);
        if (interfaceColour != null) {
            settingsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }
    }

    public void submit() {
        //When click submit, an entry should be added to the list, which is displayed as recycler in the other activity
    }

}