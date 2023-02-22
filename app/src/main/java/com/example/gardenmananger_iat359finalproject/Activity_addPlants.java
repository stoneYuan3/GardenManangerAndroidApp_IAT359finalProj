package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.plants.PlantsInfoAdd_recycler;

import java.util.ArrayList;

public class Activity_addPlants extends AppCompatActivity {

    private RecyclerView masterRecycler;
    private PlantsInfoAdd_recycler plantInfoAddRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantAdd=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        list_plantAdd.add("Tomato");
        list_plantAdd.add("Potato");

        masterRecycler=findViewById(R.id.list_plantAdd);
        plantInfoAddRecycler = new PlantsInfoAdd_recycler(list_plantAdd);

        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);

    }
}