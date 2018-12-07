package com.example.jacob.escapethecatcher;

public class Player {

    private int row;
    private int col;

    public void move(int[][] gameBoard, String button){
        if(button.equals("RIGHT")){
            if(gameBoard[row][col+1] != BoardCodes.isOBSTACLE){
                col++;
            }
        }else if(button.equals("LEFT")){
            if(gameBoard[row][col-1] != BoardCodes.isOBSTACLE){
                col--;
            }
        }else if(button.equals("UP")){
            if(gameBoard[row-1][col] != BoardCodes.isOBSTACLE){
                row--;
            }
        }else if(button.equals("DOWN")){
            if(gameBoard[row+1][col] != BoardCodes.isOBSTACLE){
                row++;
            }
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
