package com.example.jacob.lightshaker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private ShakeDetector mShakeDetector;

    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;
    ImageView lighton;
    ImageView lightoff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        constraintLayout = findViewById(R.id.constraint);
        lighton = (ImageView) layoutInflater.inflate(R.layout.light_on,null);
        lightoff = (ImageView) layoutInflater.inflate(R.layout.light_off,null);

        lighton.setX((float)0);
        lighton.setY((float)0);

        lightoff.setX((float)0);
        lightoff.setY((float)0);

        constraintLayout.addView(lightoff);

        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                constraintLayout.removeView(lightoff);
                constraintLayout.addView(lighton);
            }

            @Override
            public void onStopShake() {
                try{
                    constraintLayout.removeView(lighton);
                    constraintLayout.addView(lightoff);
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector, mSensorAccelerometer);

    }
}
