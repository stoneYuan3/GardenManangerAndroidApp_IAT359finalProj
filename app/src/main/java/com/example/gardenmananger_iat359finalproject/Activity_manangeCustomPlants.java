package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    private void listReset(){
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
        if(list_selectedPlant.size()>0){
            for(int i=0;i<list_selectedPlant.size();i++){
                String[] plantDataEach= (String[]) list_selectedPlant.get(i);
                String name=plantDataEach[1];
                boolean result = database.deleteCustomPreset(name);
                if(result){
                    Toast.makeText(this, "delete successful", Toast.LENGTH_SHORT).show();
                    listReset();
                }
                else{
                    Toast.makeText(this, "delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(this, "you havent select anything", Toast.LENGTH_SHORT).show();
        }
    }
    public void cancel(View v){
        plantInfoAddRecycler.clearList();
        Intent intent=new Intent(this,Activity_addPlants.class);
        startActivity(intent);
    }
}