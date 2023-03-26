package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoShow_recycler;

import java.util.ArrayList;


public class Frag_plantManange extends Fragment implements View.OnClickListener {

    Button button_addPlant;
    LinearLayout headerLayout;
    Context context;

    public String interfaceColour;

    private RecyclerView masterRecycler;
    private PlantsInfoShow_recycler plantInfoShowRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantShow;

    plantDatabase database;
    MyHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_plant_manange, container, false);

        button_addPlant=view.findViewById(R.id.button_addPlant);
        headerLayout=view.findViewById(R.id.headerLayout);

        context=container.getContext();
        // Inflate the layout for this fragment

        database = new plantDatabase(context);
        helper=new MyHelper(context);
        ArrayList list_userPlants=database.preparePresetPlantData("user");

        masterRecycler=view.findViewById(R.id.list_plantShow);
        plantInfoShowRecycler = new PlantsInfoShow_recycler(list_userPlants);

        masterRecycler.setAdapter(plantInfoShowRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        masterRecycler.setLayoutManager(layoutManager);

        if (interfaceColour != null) {
            headerLayout.setBackgroundColor(Color.parseColor(interfaceColour));
            button_addPlant.setBackgroundColor(Color.parseColor(interfaceColour));
        }

        return view;
    }


    @Override
    public void onClick(View v) {

    }
}