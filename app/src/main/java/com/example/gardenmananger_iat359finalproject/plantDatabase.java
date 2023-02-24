package com.example.gardenmananger_iat359finalproject;

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

    public long insertData (String name)
    {
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        long id = database.insert(Constants.DATABASE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME};
        Cursor cursor = db.query(Constants.DATABASE_NAME, columns, null, null, null, null, null);
        return cursor;
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