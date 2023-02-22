package com.example.plants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;


public class PlantsInfoAdd_recycler extends RecyclerView.Adapter<PlantsInfoAdd_recycler.PlantInfoAddView>{

//    private String title;
    private ArrayList<String> list_plant;
    public PlantsInfoAdd_recycler(ArrayList<String> list){
        this.list_plant=list;
    }

    @NonNull
    @Override
    public PlantsInfoAdd_recycler.PlantInfoAddView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_plantadd_view,parent,false);
        PlantInfoAddView plantInfoAddView = new PlantInfoAddView(v);

        return plantInfoAddView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantInfoAddView holder, int position) {

        String title=list_plant.get(position);
        holder.plantInfo_title.setText(title);
    }


    @Override
    public int getItemCount() {
        return list_plant.size();
    }

    public static class PlantInfoAddView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView plantInfo_title;

        public PlantInfoAddView(@NonNull View itemView) {
            super(itemView);
            plantInfo_title=(TextView) itemView.findViewById(R.id.plantInfo_title);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
