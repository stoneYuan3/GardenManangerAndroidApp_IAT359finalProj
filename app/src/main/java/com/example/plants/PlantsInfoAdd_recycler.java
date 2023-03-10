package com.example.plants;

import android.graphics.Color;
import android.util.Log;
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
    private ArrayList list_plant,list_userPlant;

    protected static ArrayList list_selectedPlant;

    protected static ArrayList list_addPlantList,list_removePlantList;


    public PlantsInfoAdd_recycler(ArrayList<String> list_preset,ArrayList<String> list_user){
        this.list_plant=list_preset;
        this.list_userPlant=list_user;

        for(int i=0;i<list_userPlant.size();i++){
            String[] dataEach= (String[]) list_userPlant.get(i);
            Log.d("list_userPlant", dataEach[1].toString());
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
        return plantInfoAddView;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantInfoAddView holder, int position) {

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
//        Log.d("plantinfo","name " + holder.plantName);

    }


    @Override
    public int getItemCount() {
        return list_plant.size();
    }


    public ArrayList getSelectedList(){
        return list_selectedPlant;
    }
    public ArrayList getRemoveList(){
        return list_removePlantList;
    }

    public static class PlantInfoAddView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView plantInfo_title, plantInfo_sunlight, plantInfo_soilMoist,plantInfo_temperature,plantInfo_soilPH;
        public ImageButton button_check;
        boolean isSelected;
        boolean isInUserList;

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

            button_check=itemView.findViewById(R.id.button_check);

            itemView.setOnClickListener(this);
            master=itemView.findViewById(R.id.plantadd_master);
            info=itemView.findViewById(R.id.layout_info);

            switchUI(isSelected);
        }

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
