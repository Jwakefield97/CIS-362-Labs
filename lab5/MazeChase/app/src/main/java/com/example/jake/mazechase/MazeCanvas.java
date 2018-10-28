package com.example.jake.mazechase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Stack;

public class MazeCanvas extends View {
    public final int COLS = 10;
    public final int ROWS = 9;
    final int N_CELLS = COLS*ROWS;
    final int SIZE = 100;
    final int OFFSET = 100;

    public MazeCell[] board;

    private Paint paint;

    public MazeCanvas (Context context){
        super(context);

        //create cell array
        board = new MazeCell[N_CELLS];

        //create each cell in the cell array
        int cellId = 0;
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                int x = c * SIZE + OFFSET;
                int y = r * SIZE + OFFSET;
                MazeCell cell = new MazeCell(x,y,cellId);

                board[cellId] = cell;
                cellId++;

            }
        }

        //set paint for the maze
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2.0f);

        //use back tracker method to break down walls
        backtrackMaze();

    }

    public void onDraw(Canvas canvas){
        //fill white
        canvas.drawRGB(255,255,255);

        //draw cell lines
        for(int i = 0; i < N_CELLS; i++){
            int x = board[i].x;
            int y = board[i].y;

            if(board[i].north){
                canvas.drawLine(x,y,x+SIZE, y, paint);
            }
            if(board[i].south){
                canvas.drawLine(x,y+SIZE,x+SIZE, y+SIZE, paint);
            }
            if(board[i].east){
                canvas.drawLine(x+SIZE,y,x+SIZE, y+SIZE, paint);
            }
            if(board[i].west){
                canvas.drawLine(x,y,x, y+SIZE, paint);
            }
        }
    }

    public void backtrackMaze(){
        //create backtracker vars
        Stack<Integer> stack = new Stack<Integer>();
        int top;

        //visit first cell and push to top
        int visitedCells = 1;
        int cellId = 0;
        board[cellId].visited = true;
        stack.push(cellId);

        while(visitedCells < N_CELLS){
            String possibleWalls = "";
            if(board[cellId].north && cellId >= COLS){
                if(!board[cellId - COLS].visited){
                    possibleWalls += "N";
                }
            }
            if(board[cellId].west && cellId % COLS != 0){
                if(!board[cellId - 1].visited){
                    possibleWalls += "W";
                }
            }
            if(board[cellId].east && cellId % COLS != COLS - 1){
                if(!board[cellId + 1].visited){
                    possibleWalls += "E";
                }
            }
            if(board[cellId].south && cellId < COLS * ROWS - COLS){
                if(!board[cellId + COLS].visited){
                    possibleWalls += "S";
                }
            }

            //randomly select wall from available walls
            if(possibleWalls.length() > 0){
                int index = Math.round((int)(Math.random()*possibleWalls.length()));
                char randomWall = possibleWalls.charAt(index);

                switch(randomWall){
                    case 'N':
                        board[cellId].north = false;
                        board[cellId - COLS].south = false;
                        cellId -= COLS;
                        break;
                    case 'S':
                        board[cellId].south = false;
                        board[cellId + COLS].north = false;
                        cellId += COLS;
                        break;
                    case 'E':
                        board[cellId].east = false;
                        board[cellId + 1].west = false;
                        cellId ++;
                        break;
                    case 'W':
                        board[cellId].west = false;
                        board[cellId - 1].east = false;
                        cellId --;
                        break;
                }
                board[cellId].visited = true;
                stack.push(cellId);
                visitedCells++;
            }else{               //if no walls to bust down
                top = stack.pop();
                if(top == cellId){
                    cellId = stack.pop();
                    stack.push(cellId);
                }
            }

        }

    }
}
