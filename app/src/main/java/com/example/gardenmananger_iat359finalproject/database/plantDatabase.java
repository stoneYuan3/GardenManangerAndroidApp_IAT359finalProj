package com.example.gardenmananger_iat359finalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class plantDatabase {
private SQLiteDatabase database;
private Context context;
private final MyHelper helper;


    public plantDatabase(Context cont) {
        context = cont;
        helper = new MyHelper(context);
    }

    public long insertUserPlants (String[] dataArr)
    {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.NAME, dataArr[0]);
        contentValues.put(Constants.ICON, dataArr[1]);
        contentValues.put(Constants.REQ_SUNLIGHT, dataArr[2]);
        contentValues.put(Constants.REQ_HUMIDITY, dataArr[3]);
        contentValues.put(Constants.REQ_TEMPERATURE, dataArr[4]);
        contentValues.put(Constants.REQ_SOILPH, dataArr[5]);

        long id = database.insert(Constants.TABLE_USERADD_PLANTS_NAME, null, contentValues);
        return id;
    }
    public void deleteUserPlantsByName(String name) {
        database = helper.getWritableDatabase();
        database.execSQL("DELETE FROM " + Constants.TABLE_USERADD_PLANTS_NAME
                + " WHERE " + Constants.NAME + " = '" + name + "'");
//        return id;
    }

    public Cursor getPresetPlantData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.ICON,Constants.REQ_SUNLIGHT,
                Constants.REQ_HUMIDITY,Constants.REQ_TEMPERATURE,Constants.REQ_SOILPH};
        Cursor cursor = db.query(Constants.TABLE_PRESET_PLANTS_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    //pull preset plant data and turn them into an arraylist
    //for the add plants recycler view to generate UI
    public ArrayList PreparePresetPlantData(String option){

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.ICON,Constants.REQ_SUNLIGHT,
                Constants.REQ_HUMIDITY,Constants.REQ_TEMPERATURE,Constants.REQ_SOILPH};

        Cursor cursor;
        switch (option){
            case "user":
                cursor = db.query(Constants.TABLE_USERADD_PLANTS_NAME, columns, null, null, null, null, null);
                break;
            default:
                cursor = db.query(Constants.TABLE_PRESET_PLANTS_NAME, columns, null, null, null, null, null);
                break;
        }

        int index1 = cursor.getColumnIndex(Constants.UID);
        int index2 = cursor.getColumnIndex(Constants.NAME);
        int index3 = cursor.getColumnIndex(Constants.ICON);
        int index4 = cursor.getColumnIndex(Constants.REQ_SUNLIGHT);
        int index5 = cursor.getColumnIndex(Constants.REQ_HUMIDITY);
        int index6 = cursor.getColumnIndex(Constants.REQ_TEMPERATURE);
        int index7 = cursor.getColumnIndex(Constants.REQ_SOILPH);

        ArrayList mArrayList = new ArrayList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String plantName = cursor.getString(index1);
            String plantType = cursor.getString(index2);
            String plantIcon = cursor.getString(index3);
            String plantSunlight = cursor.getString(index4);
            String plantHumidity = cursor.getString(index5);
            String plantTemperature = cursor.getString(index6);
            String plantPh = cursor.getString(index7);

            String[] plantDataEach={
                    plantName,plantType,plantIcon,plantSunlight,plantHumidity,plantTemperature,plantPh
            };
            mArrayList.add(plantDataEach);
            cursor.moveToNext();
        }
        return mArrayList;
    }


    public String getSelectedData(String type)
    {
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {Constants.NAME, Constants.SUNLIGHT, Constants.TEMPERATURE, Constants.MOIST, Constants.PH};

        Cursor cursor = database.query(Constants.DATABASE_NAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(Constants.NAME);
            int index2 = cursor.getColumnIndex(Constants.SUNLIGHT);
            int index3 = cursor.getColumnIndex(Constants.TEMPERATURE);
            int index4 = cursor.getColumnIndex(Constants.MOIST);
            int index5 = cursor.getColumnIndex(Constants.PH);
            String plantName = cursor.getString(index1);
            String plantSunlight = cursor.getString(index2);
            String plantTemperature = cursor.getString(index3);
            String plantMoist = cursor.getString(index4);
            String plantPh = cursor.getString(index5);
            buffer.append(plantName + "," + plantSunlight + "," + plantTemperature + plantMoist + plantPh + "\n");
        }
        return buffer.toString();
    }

}