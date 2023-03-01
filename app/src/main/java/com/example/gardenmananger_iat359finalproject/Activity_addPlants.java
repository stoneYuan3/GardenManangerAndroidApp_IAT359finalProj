package com.example.gardenmananger_iat359finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.database.plantDatabase;
import com.example.plants.PlantsInfo;
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
    String plantName, plantSunlight, plantTemperature, plantMoist, plantPh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plants);

        button_confirm=findViewById(R.id.button_confirm);

        list_plantAdd.add("Tomato");
        list_plantAdd.add("Potato");

        masterRecycler=findViewById(R.id.list_plantAdd);
        plantInfoAddRecycler = new PlantsInfoAdd_recycler(list_plantAdd);

        masterRecycler.setAdapter(plantInfoAddRecycler);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        masterRecycler.setLayoutManager(layoutManager);

        database = new plantDatabase(this);

    }
    public void getSelected(View v){
        list_selectedPlant=plantInfoAddRecycler.getSelectedList();
        for(int i=0;i<list_selectedPlant.size();i++){
            Log.d("selectedList",String.valueOf(list_selectedPlant.get(i)));
        }


        String name = PlantsInfo.name;
        String sunlight = PlantsInfo.name;
        String temperature = PlantsInfo.name;
        String moist = PlantsInfo.name;
        String ph = PlantsInfo.name;

        Toast.makeText(this, name + sunlight + temperature + moist + ph, Toast.LENGTH_SHORT).show();
        long id = database.insertData(name, sunlight, temperature, moist, ph);
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }

    }
}