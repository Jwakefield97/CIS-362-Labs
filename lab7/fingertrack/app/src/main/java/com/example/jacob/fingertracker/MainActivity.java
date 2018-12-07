package com.example.jacob.fingertracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    DrawCanvas drawCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawCanvas = new DrawCanvas(this);
        drawCanvas.setBackgroundColor(Color.WHITE);
        setContentView(drawCanvas);
    }

}
