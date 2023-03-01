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

    public long insertData (String name)
    {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        long id = database.insert(Constants.DATABASE_NAME, null, contentValues);
        return id;
    }

    public Cursor getPresetPlantData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.ICON,Constants.REQ_SUNLIGHT,
                Constants.REQ_HUMIDITY,Constants.REQ_TEMPERATURE,Constants.REQ_SOILPH};
        Cursor cursor = db.query(Constants.TABLE_PRESET_PLANTS_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public ArrayList PreparePresetPlantData(){
        Cursor cursor = getPresetPlantData();

        int index1 = cursor.getColumnIndex(Constants.UID);
        int index2 = cursor.getColumnIndex(Constants.NAME);
        int index3 = cursor.getColumnIndex(Constants.ICON);
        int index4 = cursor.getColumnIndex(Constants.REQ_SUNLIGHT);

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String plantName = cursor.getString(index1);
            String plantType = cursor.getString(index2);
            String plantLocation = cursor.getString(index3);
            String plantLatin = cursor.getString(index4);

            String s = plantName +"," + plantType +","+plantLocation+","+plantLatin;
            mArrayList.add(s);
            cursor.moveToNext();
        }
        return mArrayList;
    }


    public String getSelectedData(String type)
    {
        SQLiteDatabase database = helper.getWritableDatabase();
        String[] columns = {Constants.NAME};

        Cursor cursor = database.query(Constants.DATABASE_NAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(Constants.NAME);
            String plantName = cursor.getString(index1);
            buffer.append(plantName + "\n");
        }
        return buffer.toString();
    }

}