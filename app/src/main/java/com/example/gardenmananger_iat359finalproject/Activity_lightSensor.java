package com.example.gardenmananger_iat359finalproject;

import static android.hardware.Sensor.TYPE_LIGHT;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;

public class Activity_lightSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorMaster;
    private Sensor sensor_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        sensorMaster=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorMaster.getDefaultSensor(TYPE_LIGHT)!=null){
            sensor_light=sensorMaster.getDefaultSensor(TYPE_LIGHT);
        }

    }

    protected void onResume(){
        super.onResume();
        sensorMaster.registerListener(this,sensor_light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        sensorMaster.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==TYPE_LIGHT){
            float[] lightValAll = event.values;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}