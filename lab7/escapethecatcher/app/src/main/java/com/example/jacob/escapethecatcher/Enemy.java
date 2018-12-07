package com.example.jacob.escapethecatcher;

public class Enemy {

    private int row;
    private int col;

    public void move(int[][] gameBoard, int preyCol, int preyRow){
        if(col < preyCol && gameBoard[row][col+1] == BoardCodes.isEMPTY){
            col++;
        }else if(col > preyCol && gameBoard[row][col-1] == BoardCodes.isEMPTY){
            col--;
        }else if(row < preyRow && gameBoard[row+1][col] == BoardCodes.isEMPTY){
            row++;
        }else if(row > preyRow && gameBoard[row-1][col] == BoardCodes.isEMPTY){
            row--;
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
