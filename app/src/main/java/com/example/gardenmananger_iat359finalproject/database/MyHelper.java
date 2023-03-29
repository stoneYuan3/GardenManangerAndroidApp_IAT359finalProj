package com.example.gardenmananger_iat359finalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.gardenmananger_iat359finalproject.R;

public class MyHelper extends SQLiteOpenHelper {
    private Context context;

//Define apps preset plants to select from
    private String[][] presetPlants = {
            {"Tomato", String.valueOf(R.drawable.tomato),"6-8 Hours", "60%", "15-30C","6-8 ph"},
            {"Potato",String.valueOf(R.drawable.potato),"6-8 Hours", "60%", "15-30C","6-8 ph"},
            {"Lettuce",String.valueOf(R.drawable.lettuce),"4-6 Hours", "70%", "5-20C","6-8 ph"},
            {"Carrot",String.valueOf(R.drawable.carrot),"6-8 Hours", "70%", "5-20C","6-8 ph"},
            {"Kale",String.valueOf(R.drawable.kale),"6-8 Hours", "70%", "5-20C","6-8 ph"},
//            {"Onion",String.valueOf(R.drawable.onion),"6-8 Hours", "70%", "5-20C","6-8 ph"},
            {"Spinach",String.valueOf(R.drawable.spinach),"4-6 Hours", "70%", "5-20C","6-8 ph"},
            {"Beet",String.valueOf(R.drawable.beet),"6-8 Hours", "70%", "5-20C","6-8 ph"},
//            {"Brocoili",String.valueOf(R.drawable.broccoli),"6-8 Hours", "70%", "5-20C","6-8 ph"},
            {"Basil",String.valueOf(R.drawable.basil),"6-8 Hours", "70%", "5-20C","6-8 ph"},
//            {"Cucumber",String.valueOf(R.drawable.cucumber),"6-8 Hours", "70%", "5-20C","6-8 ph"}
    };

//    add plants table
    private static final String CREATE_TABLE_PRESET_PLANTS =
            "CREATE TABLE "+
                    Constants.TABLE_PRESET_PLANTS_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.NAME + " TEXT," + Constants.ICON + " INT," +
                    Constants.REQ_SUNLIGHT + " TEXT," + Constants.REQ_HUMIDITY + " TEXT,"
                    + Constants.REQ_TEMPERATURE + " TEXT,"
                    + Constants.REQ_SOILPH + " TEXT " +  ");" ;

    private static final String CREATE_TABLE_USERADD_PLANTS =
            "CREATE TABLE "+
                    Constants.TABLE_USERADD_PLANTS_NAME + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.NAME + " TEXT," + Constants.ICON + " INT," +
                    Constants.REQ_SUNLIGHT + " TEXT," + Constants.REQ_HUMIDITY + " TEXT,"
                    + Constants.REQ_TEMPERATURE + " TEXT,"
                    + Constants.REQ_SOILPH + " TEXT " +  ");" ;

//    custom plants table
    private static final String CREATE_TABLE_CUSTOM_PRESET_PLANTS =
            "CREATE TABLE "+
                    Constants.TABLE_CUSTOM_PRESET_PLANTS + " (" +
                    Constants.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.NAME + " TEXT," + Constants.ICON + " INT," +
                    Constants.REQ_SUNLIGHT + " TEXT," + Constants.REQ_HUMIDITY + " TEXT,"
                    + Constants.REQ_TEMPERATURE + " TEXT,"
                    + Constants.REQ_SOILPH + " TEXT " +  ");" ;

//    harvest records table
    private static final String CREATE_TABLE_HARVEST_RECORD =
            "CREATE TABLE "+
                    Constants.TABLE_HARVEST_RECORD_NAME + " (" +
                    Constants.UID_RECORD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constants.HARVEST_PLANT + " TEXT," +
                    Constants.HARVEST_AMOUNT + " TEXT," +
                    Constants.HARVEST_STARTDATE + " TEXT," +
                    Constants.HARVEST_HARVEST_DATE + " TEXT, " + Constants.HARVEST_PHOTO + " TEXT " + ");" ;

//    private static final String INSERT_PRESET_PLANT =
//            "INSERT INTO" + Constants.TABLE_PRESET_PLANTS_NAME +
//                    "(" + Constants.ICON + " " + Constants.REQ_SUNLIGHT + " " + Constants.REQ_HUMIDITY
//                    + " " + Constants.REQ_TEMPERATURE + " " + Constants.REQ_SOILPH +
//                    " ) " + "VALUES (1, 'John Doe', 'johndoe@example.com');" ;

    private static final String DROP_PRESET_TABLE = "DROP TABLE IF EXISTS " +Constants.TABLE_PRESET_PLANTS_NAME;
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " +Constants.TABLE_USERADD_PLANTS_NAME;
    private static final String DROP_USERPRESET_TABLE = "DROP TABLE IF EXISTS " +Constants.TABLE_CUSTOM_PRESET_PLANTS;

    private static final String DROP_HARVEST = "DROP TABLE IF EXISTS " +Constants.TABLE_HARVEST_RECORD_NAME;

    public MyHelper(Context context){
        super (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

//    create each table and put values in using contentValues
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_PRESET_PLANTS);
            db.execSQL(CREATE_TABLE_USERADD_PLANTS);
            db.execSQL(CREATE_TABLE_CUSTOM_PRESET_PLANTS);
            db.execSQL(CREATE_TABLE_HARVEST_RECORD);

            for(int i=0;i<presetPlants.length; i++){
                String[] plantEach=presetPlants[i];
                String name=plantEach[0];
                String icon=plantEach[1];
                String sunlight=plantEach[2];
                String humidity=plantEach[3];
                String temperature=plantEach[4];
                String ph=plantEach[5];
                try{
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Constants.NAME, name);
                    contentValues.put(Constants.ICON, icon);
                    contentValues.put(Constants.REQ_SUNLIGHT, sunlight);
                    contentValues.put(Constants.REQ_HUMIDITY, humidity);
                    contentValues.put(Constants.REQ_TEMPERATURE, temperature);
                    contentValues.put(Constants.REQ_SOILPH, ph);

                    db.insert(Constants.TABLE_PRESET_PLANTS_NAME, null, contentValues);

                }catch (SQLException e){
                    Toast.makeText(context, "exception when inserting preset plants", Toast.LENGTH_LONG).show();
                }
            }

            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() database", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        try {
            database.execSQL(DROP_PRESET_TABLE);
            database.execSQL(DROP_USER_TABLE);
            database.execSQL(DROP_HARVEST);
            database.execSQL(DROP_USERPRESET_TABLE);
            onCreate(database);
            Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_SHORT).show();
        }
    }
}