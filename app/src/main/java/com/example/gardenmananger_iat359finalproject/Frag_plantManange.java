package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gardenmananger_iat359finalproject.database.Constants;
import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoAdd_recycler;
import com.example.plants.PlantsInfoShow_recycler;

import java.util.ArrayList;


public class Frag_plantManange extends Fragment implements View.OnClickListener {

    Button button_addPlant;
    Context context;

    private RecyclerView masterRecycler;
    private PlantsInfoShow_recycler plantInfoShowRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantShow;

    private plantDatabase database;
    private MyHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_plant_manange, container, false);

        button_addPlant=view.findViewById(R.id.button_addPlant);

        context=container.getContext();
        // Inflate the layout for this fragment
        list_plantShow=new ArrayList<String>();
        list_plantShow.add("Tomato");
        list_plantShow.add("Potato");

        masterRecycler=view.findViewById(R.id.list_plantShow);
        plantInfoShowRecycler = new PlantsInfoShow_recycler(list_plantShow);

        masterRecycler.setAdapter(plantInfoShowRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        masterRecycler.setLayoutManager(layoutManager);


        database = new plantDatabase(context);
        helper = new MyHelper(context);

        Cursor cursor = database.getData();

        int index1 = cursor.getColumnIndex(Constants.NAME);
        int index2 = cursor.getColumnIndex(Constants.SUNLIGHT);
        int index3 = cursor.getColumnIndex(Constants.TEMPERATURE);
        int index4 = cursor.getColumnIndex(Constants.MOIST);
        int index5 = cursor.getColumnIndex(Constants.PH);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String plantName = cursor.getString(index1);
            String plantSunlight = cursor.getString(index2);
            String plantTemperature = cursor.getString(index3);
            String plantMoist = cursor.getString(index4);
            String plantPh = cursor.getString(index5);
            String s = plantName + "," + plantSunlight + "," + plantTemperature + "," + plantMoist + "," + plantPh;
            list_plantShow.add(s);
            cursor.moveToNext();
        }

        return view;
    }


    @Override
    public void onClick(View v) {

    }
}