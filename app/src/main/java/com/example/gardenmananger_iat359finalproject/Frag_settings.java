package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Frag_settings extends Fragment implements RadioGroup.OnCheckedChangeListener {

    Switch nightMode;
    Context context;
    private RadioGroup interfaceColourRadioGroup;
    String id;
    public TextView settingsTextView;
    public String interfaceColour;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_settings, container, false);

        context = container.getContext();
        interfaceColourRadioGroup = view.findViewById(R.id.interfaceColourRadioGroup);
        interfaceColourRadioGroup.setOnCheckedChangeListener(this);
        settingsTextView = view.findViewById(R.id.settingsTextView);
        if (interfaceColour != null) {
        settingsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }

        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        switch(i) {
            case R.id.greenRB:
                id = "74C161";
                break;
            case R.id.blueRB:
                id = "738ADE";
                break;
            case R.id.redRB:
                id = "C63F3F";
                break;
            case R.id.yellowRB:
                id = "EDE27D";
                break;
            case R.id.orangeRB:
                id = "E48956";
                break;
            case R.id.purpleRB:
                id = "9B5DD9";
                break;
        }
    }

}