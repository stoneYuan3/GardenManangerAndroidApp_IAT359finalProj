package com.example.plants;

import android.graphics.drawable.Drawable;

import java.lang.reflect.Array;

public class PlantsInfo {

    protected int plant_id;
    protected String name;
    protected Drawable icon_plant;
    protected String sunlight,temperature,moist,ph;

    public PlantsInfo(int id, String name, Drawable plantIcon, String sunlight, String temperature,String moist, String ph){
        this.plant_id=id;
        this.name=name;
        this.icon_plant=plantIcon;
        this.sunlight=sunlight;
        this.temperature=temperature;
        this.moist=moist;
        this.ph=ph;
    }

    public int getPlantId(){
        return plant_id;
    }
    public String getPlantName(){
        return name;
    }
    public Drawable getPlantAvatar(){
        return icon_plant;
    }
    public String[] getPlantData(){
        String[] plantDataArr={sunlight,moist,temperature,ph};
        return plantDataArr;
    }

}
