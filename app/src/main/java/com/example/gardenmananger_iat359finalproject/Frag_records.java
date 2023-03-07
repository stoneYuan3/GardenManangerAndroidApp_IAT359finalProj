package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
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

import com.example.plants.plantsRecord_recycler;

import java.util.ArrayList;


public class Frag_records extends Fragment implements View.OnClickListener {
    Button addRecordButton;
    Context context;
    public String interfaceColour;
    private TextView settingsTextView;
    private RecyclerView recordsRecycler;
    private ArrayList<String> list_plantRecords=new ArrayList<String>();
    private LinearLayoutManager layoutManager;
    private com.example.plants.plantsRecord_recycler PlantsRecord_recycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_records, container, false);

        addRecordButton = view.findViewById(R.id.addRecordButton);
        context = container.getContext();
        settingsTextView = view.findViewById(R.id.settingsTextView);

        if (interfaceColour != null) {
            settingsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }

        return view;
    }
        recordsRecycler = (RecyclerView) view.findViewById(R.id.recordsRecycler);
        PlantsRecord_recycler = new plantsRecord_recycler(list_plantRecords);
        list_plantRecords.add("Tomato");
        list_plantRecords.add("Potato");

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