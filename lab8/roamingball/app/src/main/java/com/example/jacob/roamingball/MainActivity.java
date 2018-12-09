package com.example.jacob.roamingball;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;

    private LayoutInflater layoutInflater;
    private ConstraintLayout mainLayout;
    private ImageView ballImage;
    private Ball ball;

    private Thread movementThread;

    static int TOP;
    static int BOTTOM;
    static int LEFT;
    static int RIGHT;

    private TextView x_axis;
    private TextView y_axis;
    private TextView z_axis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.constraintLayout);

        x_axis = findViewById(R.id.textView4);
        y_axis = findViewById(R.id.textView5);
        z_axis = findViewById(R.id.textView6);

        ball = new Ball();

        initializeBall();
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ballImage = (ImageView) layoutInflater.inflate(R.layout.ball_item,null);
        ballImage.setX(50.0f);
        ballImage.setY(50.0f);
        mainLayout.addView(ballImage,0);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        movementThread = new Thread(BallMovement);

    }

    private void initializeBall(){
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        ball.setX(50.0f);
        ball.setY(50.0f);
        ball.setWidth(225);

        ball.setVelocityX(0.0f);
        ball.setVelocityY(0.0f);

        TOP = 0;
        BOTTOM = screenHeight - ball.getWidth();
        LEFT = 0;
        RIGHT = screenWidth - ball.getWidth();
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        movementThread.start();
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,sensorAccelerometer);
    }

    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onDestroy(){
        finish();
        super.onDestroy();
    }

    public void onSensorChanged(SensorEvent sensorEvent){
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ball.setVelocityX(sensorEvent.values[0]);
            ball.setVelocityY(sensorEvent.values[1]);

            x_axis.setText(" "+sensorEvent.values[0]);
            y_axis.setText(" "+sensorEvent.values[1]);
            z_axis.setText(" "+sensorEvent.values[2]);

        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1){ }

    private Runnable BallMovement = new Runnable() {
        private static final int DELAY = 20;

        @Override
        public void run() {
            try {
                while(true){
                    ball.setX(ball.getX() - ball.getVelocityX());
                    ball.setY(ball.getY() + ball.getVelocityY());

                    if(ball.getY() < TOP){
                        ball.setY(TOP);
                    }else if (ball.getY() > BOTTOM){
                        ball.setY(BOTTOM);
                    }

                    if(ball.getX() < LEFT){

                    }else if (ball.getX() > RIGHT){
                        ball.setX(RIGHT);
                    }

                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public Handler threadHandler = new Handler(){
        public void handleMessage(Message msg){
            ballImage.setX(ball.getX());
            ballImage.setY(ball.getY());
        }
    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
