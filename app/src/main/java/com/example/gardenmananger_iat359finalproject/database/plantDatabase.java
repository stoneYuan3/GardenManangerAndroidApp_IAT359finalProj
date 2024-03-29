package com.example.gardenmananger_iat359finalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gardenmananger_iat359finalproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class plantDatabase {
private SQLiteDatabase database;
private Context context;
private final MyHelper helper;


    public plantDatabase(Context cont) {
        context = cont;
        helper = new MyHelper(context);
    }

//    Main plants database
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

//    harvest record database
    public long insertHarvestRecord (ArrayList dataArr)
    {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //0 is name, 1 is amount, 2 is start date, 3 is end date, 4 is photo link
        contentValues.put(Constants.HARVEST_PLANT, (String) dataArr.get(0));
        contentValues.put(Constants.HARVEST_AMOUNT, (String) dataArr.get(1));
        contentValues.put(Constants.HARVEST_STARTDATE, (String) dataArr.get(2));
        contentValues.put(Constants.HARVEST_HARVEST_DATE, (String) dataArr.get(3));
        contentValues.put(Constants.HARVEST_PHOTO, (String) dataArr.get(4));

        long id = database.insert(Constants.TABLE_HARVEST_RECORD_NAME, null, contentValues);
        return id;
    }

//    custom plants
    public long insertUserCustomPreset (ArrayList dataArr){
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(int i=0;i<dataArr.size();i++){
            if(dataArr.get(i).equals("")){
                dataArr.set(i,"not defined");
            }
        }
        //0 is name, 1 is sun, 2 is humid, 3 is temperature, 4 is ph
        contentValues.put(Constants.NAME, (String) dataArr.get(0));
        //generic icon for all custom plants
        contentValues.put(Constants.ICON,String.valueOf(R.drawable.ic_baseline_local_florist_96));
        contentValues.put(Constants.REQ_SUNLIGHT, (String) dataArr.get(1));
        contentValues.put(Constants.REQ_HUMIDITY, (String) dataArr.get(2));
        contentValues.put(Constants.REQ_TEMPERATURE, (String) dataArr.get(3));
        contentValues.put(Constants.REQ_SOILPH, (String) dataArr.get(4));

        long id = database.insert(Constants.TABLE_CUSTOM_PRESET_PLANTS, null, contentValues);
        return id;
    }

    //delete custom presets
    public boolean deleteCustomPreset(String plantName) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String whereClause = Constants.NAME + " = ?";
        String[] whereArgs = { plantName };
        int rowsDeleted = database.delete(Constants.TABLE_CUSTOM_PRESET_PLANTS, whereClause, whereArgs);
        return rowsDeleted > 0;
    }
    //delete plant when deselected by user
    public void deleteUserPlantsByName(String name) {
        database = helper.getWritableDatabase();
        database.execSQL("DELETE FROM " + Constants.TABLE_USERADD_PLANTS_NAME
                + " WHERE " + Constants.NAME + " = '" + name + "'");
    }

    public Cursor getPresetPlantData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.ICON,Constants.REQ_SUNLIGHT,
                Constants.REQ_HUMIDITY,Constants.REQ_TEMPERATURE,Constants.REQ_SOILPH};
        Cursor cursor = db.query(Constants.TABLE_PRESET_PLANTS_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    //pull harvest record info from database and turn it into arraylist for recycler view
    public ArrayList prepareHarvestInfo(){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID_RECORD, Constants.HARVEST_PLANT, Constants.HARVEST_AMOUNT,Constants.HARVEST_STARTDATE,
                Constants.HARVEST_HARVEST_DATE, Constants.HARVEST_PHOTO};

        Cursor cursor;
        cursor = db.query(Constants.TABLE_HARVEST_RECORD_NAME, columns, null, null, null, null, null);

        int index1 = cursor.getColumnIndex(Constants.UID_RECORD);
        int index2 = cursor.getColumnIndex(Constants.HARVEST_PLANT);
        int index3 = cursor.getColumnIndex(Constants.HARVEST_AMOUNT);
        int index4 = cursor.getColumnIndex(Constants.HARVEST_STARTDATE);
        int index5 = cursor.getColumnIndex(Constants.HARVEST_HARVEST_DATE);
        int index6 = cursor.getColumnIndex(Constants.HARVEST_PHOTO);

        ArrayList mArrayList = new ArrayList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String HarvestTitle = cursor.getString(index2);
            String HarvestAmount = cursor.getString(index3);
            String HarvestStartDate = cursor.getString(index4);
            String HarvestEndDate = cursor.getString(index5);
            String HarvestPhoto = cursor.getString(index6);
            String[] plantDataEach={
                    HarvestTitle,HarvestAmount,HarvestStartDate,HarvestEndDate, HarvestPhoto
            };
            mArrayList.add(plantDataEach);
            cursor.moveToNext();
        }
        return mArrayList;
    }

    //pull preset plant data and turn them into an arraylist
    //for the add plants recycler view to generate the list of plant data
    public ArrayList preparePresetPlantData(String option){

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.ICON,Constants.REQ_SUNLIGHT,
                Constants.REQ_HUMIDITY,Constants.REQ_TEMPERATURE,Constants.REQ_SOILPH};

        Cursor cursor;
        switch (option){
            case "user":
                cursor = db.query(Constants.TABLE_USERADD_PLANTS_NAME, columns, null, null, null, null, null);
                break;
            case "userPreset":
                cursor = db.query(Constants.TABLE_CUSTOM_PRESET_PLANTS, columns, null, null, null, null, null);
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
            String plantID = cursor.getString(index1);
            String plantName = cursor.getString(index2);
            String plantIcon = cursor.getString(index3);
            String plantSunlight = cursor.getString(index4);
            String plantHumidity = cursor.getString(index5);
            String plantTemperature = cursor.getString(index6);
            String plantPh = cursor.getString(index7);

            String[] plantDataEach={
                    plantID,plantName,plantIcon,plantSunlight,plantHumidity,plantTemperature,plantPh
            };
            mArrayList.add(plantDataEach);
            cursor.moveToNext();
        }
        return mArrayList;
    }

}