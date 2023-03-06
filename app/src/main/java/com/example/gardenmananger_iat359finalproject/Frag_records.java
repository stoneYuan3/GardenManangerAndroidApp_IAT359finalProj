package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Frag_records extends Fragment implements View.OnClickListener {
    Button addRecordButton;
    Context context;
    public String interfaceColour;
    private TextView settingsTextView;
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

//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.fragment_frag_plant_manange, container, false);
//
//        button_addPlant=view.findViewById(R.id.button_addPlant);
//
//        context=container.getContext();
//        // Inflate the layout for this fragment
//        list_plantShow=new ArrayList<String>();
//        list_plantShow.add("Tomato");
//        list_plantShow.add("Potato");
//
//        masterRecycler=view.findViewById(R.id.list_plantShow);
//        plantInfoShowRecycler = new PlantsInfoShow_recycler(list_plantShow);
//
//        masterRecycler.setAdapter(plantInfoShowRecycler);
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(context);
//        masterRecycler.setLayoutManager(layoutManager);
//
//        return view;
//    }
//
//
//



    @Override
    public void onClick(View v) {

    }
}