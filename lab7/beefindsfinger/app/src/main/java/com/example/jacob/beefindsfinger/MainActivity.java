package com.example.jacob.beefindsfinger;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Thread calculateThread;
    private ConstraintLayout mainLayout;
    private ImageView beeImageView;
    private ImageView flowerImageView;

    private Flower flower;
    private Bee bee;

    private int xLocation;
    private int yLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.contraintLayout);
        xLocation = 200;
        yLocation = 200;
        addFlower();
        buildBee();

        calculateThread = new Thread(calculateAction);

    }

    private void addFlower(){
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int initialXPosition = xLocation;
        int initialYPosition = yLocation;

        flower = new Flower();
        flower.setX(initialXPosition);
        flower.setY(initialYPosition);

        flowerImageView = (ImageView) layoutInflater.inflate(R.layout.flower_image,null);
        flowerImageView.setY((float)flower.getY());
        flowerImageView.setX((float)flower.getX());
        mainLayout.addView(flowerImageView,0);
    }

    private void buildBee(){
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int initialXPosition = xLocation;
        int initialYPosition = yLocation;
        int proportionalVelocity = 10;

        bee = new Bee();
        bee.setX(initialXPosition);
        bee.setY(initialYPosition);
        bee.setVelocity(proportionalVelocity);

        beeImageView = (ImageView) layoutInflater.inflate(R.layout.bee_image,null);
        beeImageView.setY((float)bee.getY());
        beeImageView.setX((float)bee.getX());
        mainLayout.addView(beeImageView,0);
    }

    @Override
    protected void onResume(){
        calculateThread.start();
        super.onResume();
    }

    @Override
    protected void onPause(){
        finish();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        finish();
        super.onDestroy();
    }

    private Runnable calculateAction = new Runnable() {
        private static final int DELAY = 200;
        @Override
        public void run() {
            try{
                while(true){
                    bee.move(xLocation,yLocation);
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    };

    public Handler threadHandler = new Handler(){
        public void handleMessage(Message msg){
            beeImageView.setX((float)bee.getX());
            beeImageView.setY((float)bee.getY());
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int touchAction = event.getActionMasked();

        switch (touchAction){
            case MotionEvent.ACTION_DOWN: //bee finds motionless finger
                xLocation = (int) event.getX();
                yLocation = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP: //return to flower when finger is removed
                xLocation = flower.getX();
                yLocation = flower.getY();
                break;
            case MotionEvent.ACTION_MOVE: //bee follows a moving finger
                xLocation = (int) event.getX();
                yLocation = (int) event.getY();
                break;
        }
        return true;
    }
}
