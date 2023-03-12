package com.example.gardenmananger_iat359finalproject.database;

public class Constants {
    public static final String DATABASE_NAME = "plantdatabase";
    public static final String TABLE_PRESET_PLANTS_NAME = "presetPlants";
    public static final String TABLE_USERADD_PLANTS_NAME = "userAddedPlants";

    public static final String UID = "_id";
    public static final String NAME = "Name";
    public static final String ICON = "Icon";
    public static final String REQ_SUNLIGHT = "IdealSunlight";
    public static final String REQ_HUMIDITY = "IdealHumidity";
    public static final String REQ_TEMPERATURE = "IdealTemperature";
    public static final String REQ_SOILPH = "IdealSoilPH";


    public static final String TABLE_HARVEST_RECORD_NAME = "userHarvestRecords";

    public static final String UID_RECORD = "_id_record";
    public static final String HARVEST_PLANT = "plantName";
    public static final String HARVEST_AMOUNT = "harvestAmount";
    public static final String HARVEST_STARTDATE = "startDate";
    public static final String HARVEST_HARVEST_DATE = "harvestDate";
    public static final String HARVEST_PHOTO = "photo";


    public static final int DATABASE_VERSION = 15;

}