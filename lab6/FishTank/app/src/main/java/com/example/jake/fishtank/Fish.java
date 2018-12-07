package com.example.jake.fishtank;

public class Fish {

    public int x;
    public int y;


    public static final int IsHungry = 1;
    public static final int IsSwimming = 2;
    public static final int IsEating = 3;


    private int condition;
    private int velocity;
    private int stomachCapacity;
    private int foodInStomach;
    private int tankWidth;
    private int tankHeight;
    private int direction;

    private int playX, playY;
    private int foodX, foodY;

    public Fish(int xPos, int yPos, int condition, int tankWidth, int tankHeight){
        this.condition = condition;
        velocity = 3;
        stomachCapacity = 80;
        foodInStomach = stomachCapacity;
        this.tankHeight = tankHeight;
        this.tankWidth = tankWidth;
        x = xPos;
        y = yPos;


        //food and explore locations are fixed in the top and bottom of the tank
        foodY = (int) tankHeight/2-100;
        foodX = (int) (Math.ceil(Math.random() * tankWidth) - tankWidth/2);

        playY = (int) - (Math.random() * tankHeight/2)+100;
        playX = (int) (Math.ceil(Math.random() * tankWidth) - tankWidth /2);
    }

    public void move(){
        switch (condition){
            case IsSwimming:
                swim();
                break;
            case IsHungry:
                findFood();
                break;
            case IsEating:
                eatFood();
                break;
        }
    }

    private void swim(){
        foodInStomach--;

        //swim towards point of interest
        int xDistance = playX - x;
        int yDistance = playY - y;
        x += xDistance/velocity;
        y += yDistance/velocity;
        if(playX < x){
            direction = -1;
        }else{
            direction = 1;
        }

        //find another place to explore
        if(Math.abs(xDistance) < 5 && Math.abs(yDistance) < 5){
            playX = (int) (Math.ceil(Math.random()*tankWidth)-tankWidth/2);
            playY = (int) -(Math.random() * tankHeight / 2)+100;
        }

        if(foodInStomach <= 0){
            condition = IsHungry;
            //find place to eat in the bottom of the tank
            foodX = (int) (Math.ceil(Math.random()*tankWidth)-tankWidth / 2) - 100;
        }
    }

    private void findFood(){
        //swim towards food
        int xDistance = foodX - x;
        int yDistance = foodY - y;

        x += xDistance / velocity;
        y += yDistance / velocity;

        //turn fish in other direction
        if(foodX < x){
            direction = -1;
        }else{
            direction = 1;
        }

        //determine if food is found
        if(Math.abs((x - foodX)) <= 10 && Math.abs(y - foodY) <= 10){
            condition = IsEating;
        }
    }

    private void eatFood(){
        //add calories to stomach
        foodInStomach += 4;

        //determine if stomach is full
        if(foodInStomach >= stomachCapacity){
            condition = IsSwimming;

            //find new place to play
            playX = (int) (Math.ceil(Math.random()*tankWidth)-tankWidth/2);
            playY = (int) (Math.random() * tankHeight / 2) + 100;
        }
    }

    public int getFacingDirection(){
        return direction;
    }
}
