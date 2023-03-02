package com.example.plants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;

public class PlantsInfoShow_recycler extends RecyclerView.Adapter<PlantsInfoShow_recycler.PlantInfoShowView>{


    private ArrayList list_plant;
    public PlantsInfoShow_recycler(ArrayList list){
        this.list_plant=list;
    }

    @NonNull
    @Override
    public PlantsInfoShow_recycler.PlantInfoShowView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_plant_view,parent,false);

        PlantsInfoShow_recycler.PlantInfoShowView plantInfoShowView = new PlantsInfoShow_recycler.PlantInfoShowView(v);

        return plantInfoShowView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsInfoShow_recycler.PlantInfoShowView holder, int position) {

        String[] plantDataEach = (String[]) list_plant.get(position);
        //0 is id, 2 is icon
        String id=plantDataEach[0];
        String title=plantDataEach[1];
        String icon=plantDataEach[2];
        String sunlight=plantDataEach[3];
        String humidity=plantDataEach[4];
        String temperature=plantDataEach[5];
        String ph=plantDataEach[6];

        holder.plantInfo_title.setText(title);
        holder.id=id;
        holder.plantInfo_sunlight.setText(sunlight);
        holder.plantInfo_soilMoist.setText(humidity);
        holder.plantInfo_temperature.setText(temperature);
        holder.plantInfo_soilPH.setText(ph);

        holder.plantName=title;
        holder.sunlight=sunlight;
        holder.humidity=humidity;
        holder.temperature=temperature;
        holder.ph=ph;
        holder.icon=icon;
    }


    @Override
    public int getItemCount() {
        return list_plant.size();
    }

    public static class PlantInfoShowView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView plantInfo_title, plantInfo_sunlight, plantInfo_soilMoist,plantInfo_temperature,plantInfo_soilPH;
        public ImageButton button_check;
        boolean isSelected;

        public String id,plantName,sunlight,humidity,temperature,ph,icon;


        public PlantInfoShowView(@NonNull View itemView) {
            super(itemView);
            plantInfo_title=(TextView) itemView.findViewById(R.id.plantInfo_title);
            plantInfo_sunlight=(TextView) itemView.findViewById(R.id.plantInfo_sunlight);
            plantInfo_soilMoist=(TextView) itemView.findViewById(R.id.plantInfo_soilMoist);
            plantInfo_temperature=(TextView) itemView.findViewById(R.id.plantInfo_temperature);
            plantInfo_soilPH=(TextView) itemView.findViewById(R.id.plantInfo_soilPH);

            button_check=itemView.findViewById(R.id.button_check);

            itemView.setOnClickListener(this);

            isSelected=false;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
