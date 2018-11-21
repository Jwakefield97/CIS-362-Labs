package com.example.jake.fishtank;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Thread calculateMovementThread;

    private ImageView fishImageView;
    private Fish fish;
    private int tankWidth;
    private int tankHeight;
    private FrameLayout fishTankLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fishTankLayout = findViewById(R.id.container);

        //get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        tankWidth = size.x;
        tankHeight = size.y;

        //instantiate fish
        fish = new Fish(0,0,Fish.IsSwimming,tankWidth,tankHeight);

        buildTank();

        calculateMovementThread = new Thread(calculateMovement);

        calculateMovementThread.start();
    }

    private void buildTank(){
        //get inflator to add visuals items
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //add foliage
        ImageView foliageImageView = (ImageView) layoutInflater.inflate(R.layout.foliage_layout, null);

        foliageImageView.setX(0);
        foliageImageView.setY(0);
        foliageImageView.setAlpha((float) 0.97);
        fishTankLayout.addView(foliageImageView,0);

        //add virtual fish
        //add foliage
        fishImageView = (ImageView) layoutInflater.inflate(R.layout.fish_image, null);
        fishImageView.setScaleX((float) .3);
        fishImageView.setScaleY((float) .3);
        fishImageView.setX(fish.x);
        fishImageView.setY(fish.y);
        fishImageView.setAlpha((float) 0.97);
        fishTankLayout.addView(fishImageView,0);
    }

    private Runnable calculateMovement = new Runnable(){
        private static final int DELAY = 200;

        public void run(){
            try {
                while(true){
                    fish.move();
                    Thread.sleep(DELAY);
                    updateTankHandler.sendEmptyMessage(0);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

    public Handler updateTankHandler = new Handler() {
        public void handleMessage(Message msg){
            //face the fish in the right direction
            fishImageView.setScaleX((float) (.3 * fish.getFacingDirection()));

            fishImageView.setX((float) fish.x);
            fishImageView.setY((float) fish.y);
        }

    };
}
