package com.example.plants;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;


public class plantsRecord_recycler extends RecyclerView.Adapter<plantsRecord_recycler.MyViewHolder>{

    private ArrayList<String> list_plantRecord;
    public plantsRecord_recycler(ArrayList<String> list){
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
        String title=list_plantRecord.get(position);
        holder.recordName.setText(title);
    }

    @Override
    public int getItemCount() {
        return list_plantRecord.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recordName;
        public EditText titleEditText;

        public MyViewHolder(View itemView) {
            super(itemView);
            recordName=(TextView) itemView.findViewById(R.id.recordName);
            titleEditText=(EditText) itemView.findViewById(R.id.titleEditText);
        }

        @Override
        public void onClick(View view) {

        }

    }
}
