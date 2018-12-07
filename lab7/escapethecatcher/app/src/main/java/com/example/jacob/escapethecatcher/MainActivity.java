package com.example.jacob.escapethecatcher;

import android.content.Context;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private GestureDetector gesture;


    final int SQUARE = 130;
    final int OFFSET = 70;
    final int COLUMNS = 8;
    final int ROWS = 8;

    final int gameBoard[][] = {
            {1,1,1,1,1,1,1,1},
            {1,2,2,1,2,1,2,1},
            {1,2,2,2,2,2,2,1},
            {1,2,1,2,2,2,2,1},
            {1,2,2,2,2,1,2,1},
            {1,2,2,2,2,2,2,3},
            {1,2,1,2,2,2,2,1},
            {1,1,1,1,1,1,1,1}
    };

    private ArrayList<ImageView> visualObjects;
    Player player;
    Enemy enemy;

    private ConstraintLayout constraintLayout;
    private ImageView enemyIMG;
    private ImageView playerIMG;
    private ImageView obstacleIMG;
    private ImageView exitIMG;
    private int exitRow;
    private int exitCol;

    private int wins;
    private int losses;
    private TextView winsTextView;
    private TextView lossesTextView;

    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);

        winsTextView = findViewById(R.id.winsTextView);
        lossesTextView = findViewById(R.id.lossesTextView);

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        visualObjects = new ArrayList<>();
        buildLogicBoard();
        createEnemy();
        createPlayer();
        wins = 0;
        losses = 0;
        winsTextView.setText("Wins: "+wins);
        lossesTextView.setText("Losses: "+losses);

        gesture = new GestureDetector(this,this);
        gesture.setOnDoubleTapListener(this);
    }

    private void buildLogicBoard(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                if(gameBoard[row][col] == BoardCodes.isOBSTACLE){
                    obstacleIMG = (ImageView) layoutInflater.inflate(R.layout.obstacle_layout,null);
                    obstacleIMG.setX((float)col*SQUARE+OFFSET);
                    obstacleIMG.setY((float)row*SQUARE+OFFSET);
                    constraintLayout.addView(obstacleIMG,0);
                    visualObjects.add(obstacleIMG);
                }else if(gameBoard[row][col] == BoardCodes.isHOME){
                    exitIMG = (ImageView) layoutInflater.inflate(R.layout.exit_layout,null);
                    exitIMG.setX((float)col*SQUARE+OFFSET);
                    exitIMG.setY((float)row*SQUARE+OFFSET);
                    constraintLayout.addView(exitIMG,0);
                    visualObjects.add(exitIMG);
                    exitRow = 5;
                    exitCol = 7;
                }
            }
        }
    }

    private void createEnemy(){
        int row = 2;
        int col = 4;

        enemyIMG = (ImageView) layoutInflater.inflate(R.layout.enemy_layout,null);
        enemyIMG.setX((float)col*SQUARE+OFFSET);
        enemyIMG.setY((float)row*SQUARE+OFFSET);
        constraintLayout.addView(enemyIMG,0);
        visualObjects.add(enemyIMG);

        enemy = new Enemy();
        enemy.setRow(row);
        enemy.setCol(col);
    }

    private void createPlayer(){
        int row = 1;
        int col = 1;

        playerIMG = (ImageView) layoutInflater.inflate(R.layout.player_layout,null);
        playerIMG.setX((float)col*SQUARE+OFFSET);
        playerIMG.setY((float)row*SQUARE+OFFSET);
        constraintLayout.addView(playerIMG,0);
        visualObjects.add(playerIMG);

        player = new Player();
        player.setRow(row);
        player.setCol(col);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return gesture.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
        movePlayer(velocityX,velocityY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event){

    }

    @Override
    public void onShowPress(MotionEvent event){

    }

    @Override
    public boolean onDown(MotionEvent event){
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e){
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e){
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e){
        return true;
    }

    private void movePlayer(float velocityX, float velocityY){
        String direction = "undetectable";

        if(velocityX > 2500){
            direction = "RIGHT";
        }else if(velocityX < -2500){
            direction = "LEFT";
        }else if(velocityY > 2500){
            direction = "DOWN";
        }else if(velocityY < -2500){
            direction = "UP";
        }

        if(!direction.contains("undetectable")){
            player.move(gameBoard,direction);
            playerIMG.setX(player.getCol()*SQUARE*OFFSET);
            playerIMG.setY(player.getRow()*SQUARE*OFFSET);

            enemy.move(gameBoard,player.getCol(), player.getRow());
            enemyIMG.setX(enemy.getCol()*SQUARE*OFFSET);
            enemyIMG.setY(enemy.getRow()*SQUARE*OFFSET);
        }

        if(enemy.getCol() == player.getCol() && enemy.getRow() == player.getRow()){
            losses++;
            lossesTextView.setText("Losses: "+losses);
            startNewGame();
        }else if(exitRow == player.getRow() && exitCol == player.getCol()){
            wins++;
            winsTextView.setText("Wins: "+wins);
            startNewGame();
        }
    }

    private void startNewGame() {
        int howMany = visualObjects.size();
        for(int i = 0; i < howMany; i ++){
            ImageView visualObj = visualObjects.get(i);
            constraintLayout.removeView(visualObj);
        }

        visualObjects.clear();

        buildLogicBoard();

        createEnemy();
        createPlayer();
    }
}
