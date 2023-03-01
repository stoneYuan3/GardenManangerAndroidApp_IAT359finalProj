package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoAdd_recycler;

import java.util.ArrayList;

public class Activity_addPlants extends AppCompatActivity {

    private RecyclerView masterRecycler;
    private PlantsInfoAdd_recycler plantInfoAddRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantAdd=new ArrayList<String>();
    private ArrayList list_selectedPlant;

    private Button button_confirm;

    plantDatabase database;
    MyHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        button_confirm=findViewById(R.id.button_confirm);

        database = new plantDatabase(this);
        helper=new MyHelper(this);
        ArrayList list_presetPlants=database.PreparePresetPlantData();

//        list_plantAdd.add("Tomato");
//        list_plantAdd.add("Potato");

        masterRecycler=findViewById(R.id.list_plantAdd);
        plantInfoAddRecycler = new PlantsInfoAdd_recycler(list_presetPlants);

        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);

    }

    public void getSelected(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
        }
    }
}