package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag_tools extends Fragment {
    private TextView headerTextView;
    private TextView tool_lightsense;
    Context context;

    public String interfaceColour;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_tools, container, false);
        context=container.getContext();
        tool_lightsense=view.findViewById(R.id.text_tool_lightsen);
        headerTextView=view.findViewById(R.id.harvestTextView);
        if (interfaceColour != null) {
        headerTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }
        return view;
    }



}