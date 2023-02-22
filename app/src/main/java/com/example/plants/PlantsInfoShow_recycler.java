package com.example.plants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;

public class PlantsInfoShow_recycler extends RecyclerView.Adapter<PlantsInfoShow_recycler.PlantInfoShowView>{


    private ArrayList<String> list_plant;
    public PlantsInfoShow_recycler(ArrayList<String> list){
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

        String title=list_plant.get(position);
        holder.plantInfo_title.setText(title);
    }


    @Override
    public int getItemCount() {
        return list_plant.size();
    }

    public static class PlantInfoShowView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView plantInfo_title;

        public PlantInfoShowView(@NonNull View itemView) {
            super(itemView);
            plantInfo_title=(TextView) itemView.findViewById(R.id.plantInfo_title);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
