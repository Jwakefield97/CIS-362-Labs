package com.example.jacob.magneticrotation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView compassImage;

    private float currentDegree = 0.0f;

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetometer;

    private float[] accelerometer;
    private float[] geomagnetic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compassImage = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorMagnetometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        final int DELAY = 1000;

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelerometer = event.values;
        }

        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            geomagnetic = event.values;
        }

        //if both sensor caused event
        if(accelerometer != null && geomagnetic != null){
            float r[] = new float[9];
            float i[] = new float[9];

            boolean foundRotation = SensorManager.getRotationMatrix(r,i,accelerometer,geomagnetic);

            //rotation has occured
            if(foundRotation){
                float orientation[] = new float[3];
                SensorManager.getOrientation(r,orientation);

                //compute rotation angle
                float degree = (float) Math.toDegrees(orientation[0]);

                RotateAnimation animation = new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                animation.setDuration(DELAY);
                animation.setFillAfter(true);

                compassImage.startAnimation(animation);
                currentDegree = -degree;
            }

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}
}
