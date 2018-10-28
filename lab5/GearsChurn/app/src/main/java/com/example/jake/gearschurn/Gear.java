package com.example.jake.gearschurn;

public class Gear {
    private int startDegree;
    private int endDegree;

    public Gear(){
        startDegree = 0;
        endDegree = 0;
    }
    public int getStartDegree() {
        return startDegree;
    }

    public void setStartDegree(int startDegree) {
        this.startDegree = startDegree;
    }

    public int getEndDegree() {
        return endDegree;
    }

    public void setEndDegree(int endDegree) {
        this.endDegree = endDegree;
    }
}
