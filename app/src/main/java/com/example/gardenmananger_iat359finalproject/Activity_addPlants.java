package com.example.gardenmananger_iat359finalproject;

import static com.example.gardenmananger_iat359finalproject.Activity_main_plantManangement.DEFAULT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.Constants;
import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoAdd_recycler;

import java.util.ArrayList;

public class Activity_addPlants extends AppCompatActivity {

    public String interfaceColour;
    private RecyclerView masterRecycler;
    private PlantsInfoAdd_recycler plantInfoAddRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantAdd=new ArrayList<String>();
    private ArrayList list_selectedPlant,list_removePlant;

    private Button button_confirm;

    plantDatabase database;
    MyHelper helper;

    private TextView addPlantsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        button_confirm=findViewById(R.id.button_confirm);

        database = new plantDatabase(this);
        helper=new MyHelper(this);
        ArrayList list_presetPlants=database.PreparePresetPlantData("preset");
        ArrayList list_userPlants=database.PreparePresetPlantData("user");

        masterRecycler=findViewById(R.id.list_plantAdd);
        plantInfoAddRecycler = new PlantsInfoAdd_recycler(list_presetPlants,list_userPlants);

        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);

        addPlantsTextView = findViewById(R.id.addPlantsTextView);

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        interfaceColour = preferences.getString("colourId", DEFAULT);
        if (interfaceColour != null) {
            addPlantsTextView.setBackgroundColor(Color.parseColor(interfaceColour));
        }
    }

    public void getSelected(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
        }
    }

    public void insertPlants(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        list_removePlant=plantInfoAddRecycler.getRemoveList();

//        for(int i=0;i<list_selectedPlant.size();i++){
//            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
//        }
        boolean addInfo=false, removeInfo=false;
        for(int i=0;i<list_selectedPlant.size();i++){
            String[] plantDataEach= (String[]) list_selectedPlant.get(i);
            String plant_id=plantDataEach[0];
            String name=plantDataEach[1];
            String icon=plantDataEach[2];
            String sunlight=plantDataEach[3];
            String humidity=plantDataEach[4];
            String temperature=plantDataEach[5];
            String ph=plantDataEach[6];
            String[] dataArr={name, icon, sunlight, humidity,temperature,ph};
            long id = database.insertUserPlants(dataArr);
            if (id < 0)
            {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm add info fail");
            }
            else
            {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm add info successful");
                addInfo=true;
            }
        }
        for(int i=0;i<list_removePlant.size();i++){
            String[] plantDataEach= (String[]) list_removePlant.get(i);
            String name=plantDataEach[1];
            try {
                database.deleteUserPlantsByName(name);
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm remove info successful");
                removeInfo=true;
            } catch (SQLException e) {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm remove info fail");
            }

        }

        if(list_selectedPlant.size()>0 && list_removePlant.size()>0){
            if(addInfo && removeInfo){
                Intent intent=new Intent(this,Activity_main_plantManangement.class);
                startActivity(intent);            }
        }
        else{
            if(addInfo || removeInfo){
                Intent intent=new Intent(this,Activity_main_plantManangement.class);
                startActivity(intent);
            }
        }


    }
}