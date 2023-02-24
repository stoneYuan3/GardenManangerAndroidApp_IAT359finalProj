package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Frag_records extends Fragment implements View.OnClickListener {
Button addRecord;
Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addRecord = container.findViewById(R.id.addRecordButton);
        context = container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_records, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}