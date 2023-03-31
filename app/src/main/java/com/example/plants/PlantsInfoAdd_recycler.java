package com.example.plants;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gardenmananger_iat359finalproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PlantsInfoAdd_recycler extends RecyclerView.Adapter<PlantsInfoAdd_recycler.PlantInfoAddView>{

    //for Activity_addPlants
    //generate list of plant info bar that are selectable

    private ArrayList list_preset,list_plant,list_customPlant,list_userPlant;
    private Context context;

    protected static ArrayList list_selectedPlant;

    //list used to identify which item should be added or removed from the database
    protected static ArrayList list_addPlantList,list_removePlantList;

    public PlantsInfoAdd_recycler(ArrayList<String> list_preset,ArrayList<String> list_custom,ArrayList<String> list_user){
        this.list_preset=list_preset;
        this.list_userPlant=list_user;
        this.list_customPlant=list_custom;
        //when displaying only custom plants
        if(list_preset==null && list_customPlant!=null){
            list_plant=list_customPlant;
        }
        //on add plant interface: display both preset plant and custom plant
        else{
            list_plant=list_preset;
            list_plant.addAll(list_customPlant);
        }
        list_selectedPlant=new ArrayList();
        list_addPlantList=new ArrayList();
        list_removePlantList=new ArrayList();
    }

    @NonNull
    @Override
    public PlantsInfoAdd_recycler.PlantInfoAddView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_plantadd_view,parent,false);
        PlantInfoAddView plantInfoAddView = new PlantInfoAddView(v);
//        Log.d("plantinfo","name " + plantInfoAddView.plantName);
        context = parent.getContext();
        return plantInfoAddView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantInfoAddView holder, int position) {
        String[] plantDataEach = (String[]) list_plant.get(position);
        //0 is id, 2 is icon
        String id=plantDataEach[0];
        String title=plantDataEach[1];
//        Drawable icon= Drawable.createFromPath(plantDataEach[2]);
        int iconId=Integer.parseInt(plantDataEach[2]);
        Drawable icon = ContextCompat.getDrawable(context, iconId);
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
        holder.plant_icon.setImageDrawable(icon);

        holder.plantName=title;
        holder.sunlight=sunlight;
        holder.humidity=humidity;
        holder.temperature=temperature;
        holder.ph=ph;
        holder.icon=String.valueOf(iconId);

        Log.d("drawableCheck", plantDataEach[2]);

        if(list_userPlant!=null){
            //run boolean idenfication on whether this plant is selected here
            for(int i=0;i<list_userPlant.size();i++){
                String[] dataEach= (String[]) list_userPlant.get(i);
                Log.d("test","comparing dataEach[1] "+ dataEach[1]+ " with title "+title);

                if(dataEach[1].equals(title)){
                    Log.d("test",dataEach[1]+" is the same");
                    holder.isSelected=true;
                    holder.isInUserList=true;
                    holder.switchUI(holder.isSelected);
                    break;
                }
                else{
                    holder.isSelected=false;
                    holder.isInUserList=false;
                    holder.switchUI(holder.isSelected);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return list_plant.size();
    }

    //make those lists available for activities
    public ArrayList getSelectedList(){
        return list_selectedPlant;
    }
    public ArrayList getRemoveList(){
        return list_removePlantList;
    }

    public void clearList(){
        list_selectedPlant.clear();
        list_removePlantList.clear();
    }

    public class PlantInfoAddView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView plantInfo_title, plantInfo_sunlight, plantInfo_soilMoist,plantInfo_temperature,plantInfo_soilPH;
        public ImageButton button_check;
        public ImageView plant_icon;
        public boolean isSelected;
        public boolean isInUserList;

        public String id,plantName,sunlight,humidity,temperature,ph,icon;

        private ConstraintLayout master;
        private LinearLayout info;

        public PlantInfoAddView(@NonNull View itemView) {
            super(itemView);
            plantInfo_title=(TextView) itemView.findViewById(R.id.plantInfo_title);
            plantInfo_sunlight=(TextView) itemView.findViewById(R.id.plantInfo_sunlight);
            plantInfo_soilMoist=(TextView) itemView.findViewById(R.id.plantInfo_soilMoist);
            plantInfo_temperature=(TextView) itemView.findViewById(R.id.plantInfo_temperature);
            plantInfo_soilPH=(TextView) itemView.findViewById(R.id.plantInfo_soilPH);
            plant_icon = (ImageView) itemView.findViewById(R.id.plant_icon);

            button_check=itemView.findViewById(R.id.button_check);

            itemView.setOnClickListener(this);
            master=itemView.findViewById(R.id.plantadd_master);
            info=itemView.findViewById(R.id.layout_info);

            switchUI(isSelected);
        }

        //change UI when the item is selected or deselected
        @Override
        public void onClick(View v) {
            if(isSelected){
                for(int i=0;i<list_selectedPlant.size();i++){
                    String[] dataEach= (String[]) list_selectedPlant.get(i);
                    if(dataEach[1].equals(this.plantName)){
                        Log.d("clickListener",plantName+ " no." + this.getAdapterPosition() +" is removed");
                        list_selectedPlant.remove(list_selectedPlant.get(i));
                    }
                }
                //only add the item to the list of removed plants when it is in the user's plant list at the first place
                if(isInUserList){
                    String[] plantData={id,plantName,icon,sunlight,humidity,temperature,ph};
                    list_removePlantList.add(plantData);
                }

                for(int i=0;i<list_selectedPlant.size();i++){
                    String[] dataEach= (String[]) list_selectedPlant.get(i);
                    Log.d("list_selectedPlant", "list_selectedPlant has: "+dataEach[1].toString());
                }
                for(int i=0;i<list_removePlantList.size();i++){
                    String[] dataEach= (String[]) list_removePlantList.get(i);
                    Log.d("list_removePlantList", "list_removePlantList has "+dataEach[1].toString());
                }

                isSelected=false;
                switchUI(isSelected);
            }
            else{
                for(int i=0;i<list_removePlantList.size();i++){
                    String[] dataEach= (String[]) list_removePlantList.get(i);
                    if(dataEach[1].equals(this.plantName)){
                        Log.d("clickListener",plantName+ " no." + this.getAdapterPosition() +" is removed");
                        list_removePlantList.remove(list_removePlantList.get(i));
                    }
                }
                Log.d("clickListener",plantName+ " no." + this.getAdapterPosition() +" is selected");
                if(!isInUserList){
                    String[] plantData={id,plantName,icon,sunlight,humidity,temperature,ph};
                    list_selectedPlant.add(plantData);
                }

                for(int i=0;i<list_selectedPlant.size();i++){
                    String[] dataEach= (String[]) list_selectedPlant.get(i);
                    Log.d("list_selectedPlant", "list_selectedPlant has: "+dataEach[1].toString());
                }
                for(int i=0;i<list_removePlantList.size();i++){
                    String[] dataEach= (String[]) list_removePlantList.get(i);
                    Log.d("list_removePlantList", "list_removePlantList has "+dataEach[1].toString());
                }

                isSelected=true;
                switchUI(isSelected);

            }

        }

        public void switchUI(boolean bool){
            if(bool){
                button_check.setBackground(itemView.getContext().getDrawable(R.drawable.checked_24dp));
                master.setAlpha(1.0f);
            }
            else{
                button_check.setBackground(itemView.getContext().getDrawable(R.drawable.unchecked_24dp));
                master.setAlpha(0.5f);
            }
        }
    }

}
