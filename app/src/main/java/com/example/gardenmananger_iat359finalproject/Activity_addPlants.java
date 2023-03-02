package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.Constants;
import com.example.gardenmananger_iat359finalproject.database.MyHelper;
import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfoAdd_recycler;

import java.util.ArrayList;

public class Activity_addPlants extends AppCompatActivity {

    private RecyclerView masterRecycler;
    private PlantsInfoAdd_recycler plantInfoAddRecycler;
    private LinearLayoutManager layoutManager;

    private ArrayList<String> list_plantAdd=new ArrayList<String>();
    private ArrayList list_selectedPlant;

    private Button button_confirm;

    plantDatabase database;
    MyHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        button_confirm=findViewById(R.id.button_confirm);

        database = new plantDatabase(this);
        helper=new MyHelper(this);
        ArrayList list_presetPlants=database.PreparePresetPlantData("preset");

        masterRecycler=findViewById(R.id.list_plantAdd);
        plantInfoAddRecycler = new PlantsInfoAdd_recycler(list_presetPlants);

        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);
    }

    public void getSelected(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
        }
    }

    public void insertPlants(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
        }

        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            String[] plantDataEach= (String[]) list_selectedPlant.get(i);
            String name=plantDataEach[0];
            String icon=plantDataEach[1];
            String sunlight=plantDataEach[2];
            String humidity=plantDataEach[3];
            String temperature=plantDataEach[4];
            String ph=plantDataEach[5];
            String[] dataArr={name, icon, sunlight, humidity,temperature,ph};
            long id = database.insertUserPlants(dataArr);
            if (id < 0)
            {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm fail");
            }
            else
            {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                Log.d("insert","confirm successful");
                finish();
            }
        }

    }
}