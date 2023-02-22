package com.example.plants;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        public ImageButton button_check;
        boolean isSelected;

        public PlantInfoAddView(@NonNull View itemView) {
            super(itemView);
            plantInfo_title=(TextView) itemView.findViewById(R.id.plantInfo_title);
            button_check=itemView.findViewById(R.id.button_check);

            itemView.setOnClickListener(this);

            isSelected=false;
        }

        @Override
        public void onClick(View v) {
            LinearLayout info=v.findViewById(R.id.layout_info);
            ConstraintLayout master=v.findViewById(R.id.plantadd_master);
            if(isSelected){
//                info.setBackgroundColor(Color.WHITE);
                button_check.setBackground(itemView.getContext().getDrawable(R.drawable.unchecked_24dp));
                master.setAlpha(0.5f);
                isSelected=false;
            }
            else{
//                info.setBackgroundColor(Color.BLUE);
                button_check.setBackground(itemView.getContext().getDrawable(R.drawable.checked_24dp));
                master.setAlpha(1.0f);
                isSelected=true;
            }

        }
    }

}
