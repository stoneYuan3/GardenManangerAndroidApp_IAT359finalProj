package com.example.plants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;


public class plantsRecord_recycler extends RecyclerView.Adapter<plantsRecord_recycler.MyViewHolder>{

    private ArrayList list_plantRecord;
    public plantsRecord_recycler(ArrayList list){
        this.list_plantRecord=list;
    }

    @Override
    public plantsRecord_recycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_harvestrecord,parent,false);

        plantsRecord_recycler.MyViewHolder plantRecordShowView = new plantsRecord_recycler.MyViewHolder(v);

        return plantRecordShowView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        String title = holder.titleEditText.getText().toString();
        String[] harvestInfoEach = (String[]) list_plantRecord.get(position);

        String harvestTitle=harvestInfoEach[0];
        String harvestAmount=harvestInfoEach[1];
        String harvestStartDate=harvestInfoEach[2];
        String harvestEndDate=harvestInfoEach[3];

        holder.recordName.setText(harvestTitle);
        holder.recordAmount.setText(harvestAmount);
        holder.recordStartDate.setText(harvestStartDate);
        holder.recordHarvestDate.setText(harvestEndDate);

        //again, remember to put photo in

    }

    @Override
    public int getItemCount() {
        return list_plantRecord.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recordName,recordAmount,recordStartDate,recordHarvestDate;
        public EditText titleEditText;

        public MyViewHolder(View itemView) {
            super(itemView);
            recordName=(TextView) itemView.findViewById(R.id.recordName);
            recordAmount=(TextView) itemView.findViewById(R.id.recordAmount);
            recordStartDate=(TextView) itemView.findViewById(R.id.recordStartDate);
            recordHarvestDate=(TextView) itemView.findViewById(R.id.recordHarvestDate);

            //again, remember to put photo in

            titleEditText=(EditText) itemView.findViewById(R.id.input_rec_plantName);
        }

        @Override
        public void onClick(View view) {

        }

    }
}
