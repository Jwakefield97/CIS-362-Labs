package com.example.jacob.beefindsfinger;

public class Bee {
    private int x;
    private int y;
    private int velocity;

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    public int getVelocity(){
        return velocity;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void move(int destinationX, int destinationY){
        int distX = destinationX - x;
        int distY = destinationY - y;

        x += distX/velocity;
        y += distY/velocity;
    }

}
