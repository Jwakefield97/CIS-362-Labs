package com.example.jake.mazechase;

public class MazeCell {
    public int x;
    public int y;
    public int id;
    public boolean visited;
    public boolean north;
    public boolean south;
    public boolean east;
    public boolean west;

    //new cells are created with all walls intact
    public MazeCell(int xPos, int yPos, int cellId){
        x = xPos;
        y = yPos;
        visited = false;
        north = true;
        south = true;
        east = true;
        west = true;
        id=cellId;
    }
}
