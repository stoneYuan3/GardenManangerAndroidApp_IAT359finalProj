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
import android.widget.TextView;

import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.plantsRecord_recycler;

import java.util.ArrayList;


public class Frag_records extends Fragment implements View.OnClickListener {
    Button addRecordButton;
    Context context;
    public String interfaceColour;
    private TextView harvestTextView;
    private RecyclerView recordsRecycler;
    private LinearLayoutManager layoutManager;
    private com.example.plants.plantsRecord_recycler PlantsRecord_recycler;

    plantDatabase database;
    MyHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_records, container, false);

        addRecordButton = view.findViewById(R.id.addRecordButton);
        context = container.getContext();
        harvestTextView = view.findViewById(R.id.harvestTextView);

        if (interfaceColour != null) {
            addRecordButton.setBackgroundColor(Color.parseColor(interfaceColour));
        }

        database = new plantDatabase(context);
        helper=new MyHelper(context);

        //pull data from database and store in an arraylist. check plantDatabase for detail.
        ArrayList list_plantRecords=database.prepareHarvestInfo();

        //assign data to recycler view, check plantsRecord_recycler for detail
        //again, this recycler view layout does not include photo yet. remember to add it in when implementing the photo feature.
        recordsRecycler = (RecyclerView) view.findViewById(R.id.recordsRecycler);
        PlantsRecord_recycler = new plantsRecord_recycler(list_plantRecords);

        recordsRecycler.setAdapter(PlantsRecord_recycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recordsRecycler.setLayoutManager(layoutManager);

        return view;
    }
    @Override
    public void onClick(View v) {

    }
}