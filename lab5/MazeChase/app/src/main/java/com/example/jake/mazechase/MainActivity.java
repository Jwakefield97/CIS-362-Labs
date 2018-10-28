package com.example.jake.mazechase;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ImageView pig;
    private LayoutInflater layoutInflater;
    private float xPos;
    private float yPos;
    private MazeCanvas maze;

    private int cellId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xPos = 10;
        yPos = 10;
        cellId = 22;

        //construct maze and add it to layout
        maze = new MazeCanvas(this);

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.addView(maze,0);

        //create layout inflater
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //set background of imageview to pig animation
        pig = (ImageView) layoutInflater.inflate(R.layout.pig_view,null);
        pig.setBackgroundResource(R.drawable.pig_animation);


        //create pig animation drawable object based on background
        AnimationDrawable pigAnimate = (AnimationDrawable) pig.getBackground();
        pigAnimate.start();

        pig.setX(xPos);
        pig.setY(yPos);
        pig.setScaleX(.15f);
        pig.setScaleY(.15f);
        constraintLayout.addView(pig,1);
    }

    public void goUp(View view){
        if(maze.board[cellId].north == false){
            yPos -= 100;
            pig.setY(yPos);
            cellId -= maze.COLS;
        }
    }

    public void goLeft(View view){
        if(maze.board[cellId].west == false){
            xPos -= 100;
            pig.setX(xPos);
            cellId--;
        }
    }

    public void goRight(View view){
        if(maze.board[cellId].east == false){
            yPos += 100;
            pig.setX(xPos);
            cellId++;
        }
    }

    public void goDown(View view){
        if(maze.board[cellId].south == false){
            yPos += 100;
            pig.setY(yPos);
            cellId += maze.COLS;
        }
    }
}
