package com.example.plants;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;


public class plantsRecord_recycler extends RecyclerView.Adapter<plantsRecord_recycler.MyViewHolder>{

    //for frag_records
    //generate list of harvest records

    private ArrayList list_plantRecord;
    public plantsRecord_recycler(ArrayList list){
        this.list_plantRecord=list;
    }
    private Context context;

    @Override
    public plantsRecord_recycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_harvestrecord,parent,false);

        plantsRecord_recycler.MyViewHolder plantRecordShowView = new plantsRecord_recycler.MyViewHolder(v);
        context = parent.getContext();

        return plantRecordShowView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] harvestInfoEach = (String[]) list_plantRecord.get(position);

        String harvestTitle=harvestInfoEach[0];
        String harvestAmount=harvestInfoEach[1];
        String harvestStartDate=harvestInfoEach[2];
        String harvestEndDate=harvestInfoEach[3];
        String harvestPhoto = harvestInfoEach[4];

        Uri imgUri = Uri.parse(harvestPhoto);
        Log.d("imgsrc", imgUri.toString());

        holder.recordName.setText(harvestTitle);
        holder.recordAmount.setText(harvestAmount);
        holder.recordStartDate.setText(harvestStartDate);
        holder.recordHarvestDate.setText(harvestEndDate);

        if (imgUri != null && !"none".equals(imgUri.toString())) {
            holder.recordPhoto.setImageURI(imgUri);
        }

        else{
            Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_edit_note_24);
            holder.recordPhoto.setImageDrawable(icon);
        }

    }

    @Override
    public int getItemCount() {
        return list_plantRecord.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recordName,recordAmount,recordStartDate,recordHarvestDate;
        public ImageView recordPhoto;
        public EditText titleEditText;

        public MyViewHolder(View itemView) {
            super(itemView);
            recordName=(TextView) itemView.findViewById(R.id.recordName);
            recordAmount=(TextView) itemView.findViewById(R.id.recordAmount);
            recordStartDate=(TextView) itemView.findViewById(R.id.recordStartDate);
            recordHarvestDate=(TextView) itemView.findViewById(R.id.recordHarvestDate);
            recordPhoto=(ImageView) itemView.findViewById(R.id.harvestImageView);

            titleEditText=(EditText) itemView.findViewById(R.id.input_rec_plantName);
        }

        @Override
        public void onClick(View view) {

        }

    }
}
