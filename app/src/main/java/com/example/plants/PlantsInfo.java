package com.example.plants;

import android.graphics.drawable.Drawable;

import java.lang.reflect.Array;

public class PlantsInfo {


    protected String sunlight,temperature,ph,moist;
//    protected int moist;
    protected Drawable icon_plant;

    public PlantsInfo(){

    }

    public String[] getPlantData(){
        String[] plantDataArr={sunlight,moist,temperature,ph};
        return plantDataArr;
    }

    public Drawable getPlantAvatar(){
        return icon_plant;
    }

}
