package com.example.jake.gearschurn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView gear1Img;
    private ImageView gear2Img;

    private Gear gear1;
    private Gear gear2;

    private float currentDegree;
    private float degree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gear1Img = findViewById(R.id.imageView);
        gear2Img = findViewById(R.id.imageView2);

        initGears();
    }

    public void initGears(){
        gear1 = new Gear();
        gear2 = new Gear();
        gear1.setStartDegree(0);
        gear1.setEndDegree(360);
        gear2.setStartDegree(0);
        gear2.setEndDegree(-360);
    }

    public void animateGears(View view){
        final int DELAY = 1000;
        RotateAnimation ral = new RotateAnimation(gear1.getStartDegree(), gear1.getEndDegree(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ral.setDuration(DELAY);
        ral.setFillAfter(true);
        gear1Img.startAnimation(ral);

        RotateAnimation ra2 = new RotateAnimation(gear2.getStartDegree(), gear2.getEndDegree(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra2.setDuration(DELAY);
        ra2.setFillAfter(true);
        gear2Img.startAnimation(ra2);

    }

}
