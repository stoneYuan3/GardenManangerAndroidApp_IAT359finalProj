package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Frag_plantManange extends Fragment implements View.OnClickListener {

    Button button_addPlant;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        button_addPlant=container.findViewById(R.id.button_addPlant);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_plant_manange, container, false);
    }


    @Override
    public void onClick(View v) {

    }
}