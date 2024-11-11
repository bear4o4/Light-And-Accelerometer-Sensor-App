package com.example.luminositysensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Light extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private ImageView imageView;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        imageView = findViewById(R.id.imageView2);
        nextButton = findViewById(R.id.nextbutton);


        imageView.setImageResource(R.drawable.solarpanel);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            imageView.setImageResource(R.drawable.solarpanel);
        }

        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(Light.this, Face.class);
            startActivity(intent);
        });
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightLevel = sensorEvent.values[0];

            if(lightLevel < 100){
                imageView.setImageResource(R.drawable.solarpanel);
            }
            else if (lightLevel <= 500) {
                imageView.setImageResource(R.drawable.light1);
            } else if (lightLevel <= 1000) {
                imageView.setImageResource(R.drawable.light2);
            } else if (lightLevel <= 1500) {
                imageView.setImageResource(R.drawable.light3);
            } else if (lightLevel <= 2000) {
                imageView.setImageResource(R.drawable.light4);
            } else if (lightLevel <= 2500) {
                imageView.setImageResource(R.drawable.light5);
            } else if (lightLevel <= 3000) {
                imageView.setImageResource(R.drawable.light6);
            } else if (lightLevel <= 4000) {
                imageView.setImageResource(R.drawable.light7);
            }  else {
                imageView.setImageResource(R.drawable.light8);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lightSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }
}