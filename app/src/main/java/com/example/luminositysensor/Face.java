package com.example.luminositysensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
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

public class Face extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView faceImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        faceImageView = findViewById(R.id.imageView);
        Button backButton = findViewById(R.id.backbutton);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Face.this, Light.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (Math.abs(x) < 2 && Math.abs(y) < 2) {
                faceImageView.setImageResource(R.drawable.face5);
            } else if (y > 4 && Math.abs(x) < 3) {
                faceImageView.setImageResource(R.drawable.face2);
            } else if (y < -4 && Math.abs(x) < 3) {

                faceImageView.setImageResource(R.drawable.face8);
            } else if (x > 4 && Math.abs(y) < 3) {

                faceImageView.setImageResource(R.drawable.face6);
            } else if (x < -4 && Math.abs(y) < 3) {

                faceImageView.setImageResource(R.drawable.face4);
            } else if (x < -4 && y > 4) {

                faceImageView.setImageResource(R.drawable.face1);
            } else if (x > 4 && y > 4) {

                faceImageView.setImageResource(R.drawable.face3);
            } else if (x < -4 && y < -4) {
                faceImageView.setImageResource(R.drawable.face7);
            } else if (x > 4 && y < -4) {
                faceImageView.setImageResource(R.drawable.face9);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}