package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class Frag_settings extends Fragment implements RadioGroup.OnCheckedChangeListener {
    Context context;
    private RadioGroup interfaceColourRadioGroup;
    String id;
    public TextView settingsTextView;
    public String interfaceColour;

    public Button submitColour;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_settings, container, false);

        context = container.getContext();
        interfaceColourRadioGroup = view.findViewById(R.id.interfaceColourRadioGroup);
        interfaceColourRadioGroup.setOnCheckedChangeListener(this);
        settingsTextView = view.findViewById(R.id.harvestTextView);
        submitColour = view.findViewById(R.id.submitColour);
        if (interfaceColour != null) {
        settingsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        submitColour.setBackgroundColor(Color.parseColor(interfaceColour));
        }

        return view;
    }

//check which radio button has been selected, and then change id to a new colour
    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        switch(i) {
            case R.id.whiteRB:
                id = "F1F1F1";
                break;
            case R.id.pinkRB:
                id = "F5B6FF";
                break;
            case R.id.blueRB:
                id = "7799F1";
                break;
            case R.id.greenRB:
                id = "32772C";
                break;
            case R.id.yellowRB:
                id = "FFFB9D";
                break;
            case R.id.orangeRB:
                id = "E78A56";
                break;
        }
    }

}