package com.example.gardenmananger_iat359finalproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String CREATE_DATABASE =
            "CREATE TABLE "+
                    Constants.DATABASE_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.NAME + " TEXT);" ;

    private static final String DROP_DATABASE = "DROP DATABASE IF EXISTS " + Constants.DATABASE_NAME;

    public MyHelper(Context context){
        super (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_DATABASE);
            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() database", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        try {
            database.execSQL(DROP_DATABASE);
            onCreate(database);
            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_SHORT).show();
        }
    }
}