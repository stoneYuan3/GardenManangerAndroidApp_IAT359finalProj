package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoAdd_recycler;

import java.util.ArrayList;

public class Activity_manangeCustomPlants extends AppCompatActivity {

    plantDatabase database;
    MyHelper helper;

    private RecyclerView masterRecycler;
    private PlantsInfoAdd_recycler plantInfoAddRecycler;
    private LinearLayoutManager layoutManager;
    private ArrayList list_selectedPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manange_custom_plants);

        database = new plantDatabase(this);
        helper=new MyHelper(this);

        ArrayList list_customPlants=database.preparePresetPlantData("userPreset");
        list_selectedPlant=new ArrayList();

        plantInfoAddRecycler = new PlantsInfoAdd_recycler(null,list_customPlants,null);

        masterRecycler=findViewById(R.id.list_customPlants);
        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);

    }

    public void delete(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
    }
    public void cancel(View v){
        finish();
    }
}