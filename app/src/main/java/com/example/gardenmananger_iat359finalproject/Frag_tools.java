package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag_tools extends Fragment {

    TextView tool_lightsense;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tool_lightsense=container.findViewById(R.id.text_tool_lightsen);
        context=container.getContext();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_tools, container, false);
    }



}