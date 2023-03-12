package com.example.gardenmananger_iat359finalproject;

import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;

import java.util.ArrayList;

public class Activity_addRecord extends AppCompatActivity implements TextWatcher {

    public String interfaceColour;

    private TextView settingsTextView;

    private EditText input_rec_plantName,input_rec_amount,input_rec_dateStart,input_rec_dateEnd;

    plantDatabase database;
    MyHelper helper;

    private ArrayList list_harvestInfo = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        database = new plantDatabase(this);
        helper=new MyHelper(this);

        settingsTextView = findViewById(R.id.settingsTextView);

        input_rec_plantName=findViewById(R.id.input_rec_plantName);
        input_rec_amount=findViewById(R.id.input_rec_amount);
        input_rec_dateStart=findViewById(R.id.input_rec_dateStart);
        input_rec_dateEnd=findViewById(R.id.input_rec_dateEnd);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);
        if (interfaceColour != null) {
            settingsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }
    }

    public void submit(View v) {
        String rec_name= String.valueOf(input_rec_plantName.getText());
        String rec_amount= String.valueOf(input_rec_amount.getText());
        String rec_dateStart= String.valueOf(input_rec_dateStart.getText());
        String rec_dateEnd= String.valueOf(input_rec_dateEnd.getText());

        if(! (rec_name.equals("") || rec_amount.equals("") || rec_dateStart.equals("") || rec_dateEnd.equals("")) ){
            Toast.makeText(this,rec_name,Toast.LENGTH_LONG).show();

            list_harvestInfo.add(rec_name);
            list_harvestInfo.add(rec_amount);
            list_harvestInfo.add(rec_dateStart);
            list_harvestInfo.add(rec_dateEnd);

            list_harvestInfo.add("photoLink");

            long id = database.insertHarvestRecord(list_harvestInfo);
            if (id < 0)
            {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm add info fail");
            }
            else
            {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm add info successful");
                Intent intent=new Intent(this,Activity_main_plantManangement.class);
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(this, "You havent complete the form!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}