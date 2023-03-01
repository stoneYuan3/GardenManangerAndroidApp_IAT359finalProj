package com.example.gardenmananger_iat359finalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class plantDatabase {
private SQLiteDatabase database;
private Context context;
private final MyHelper helper;


    public plantDatabase(Context cont) {
        context = cont;
        helper = new MyHelper(context);
    }

    public long insertData (String name, String sunlight, String temperature, String moist, String ph)
    {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        contentValues.put(Constants.SUNLIGHT, sunlight);
        contentValues.put(Constants.TEMPERATURE, temperature);
        contentValues.put(Constants.MOIST, moist);
        contentValues.put(Constants.PH, ph);
        long id = database.insert(Constants.DATABASE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.SUNLIGHT, Constants.TEMPERATURE, Constants.MOIST, Constants.PH};
        Cursor cursor = db.query(Constants.DATABASE_NAME, columns, null, null, null, null, null);
        return cursor;
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