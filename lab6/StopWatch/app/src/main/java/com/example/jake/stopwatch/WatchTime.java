package com.example.jake.stopwatch;

public class WatchTime {


    private long startTime;
    private long timeUpdate;
    private long storedTime;

    public WatchTime(){
        startTime = 0L;
        timeUpdate = 0L;
        storedTime = 0L;
    }

    public void resetWatchTime(){
        startTime = 0L;
        timeUpdate = 0L;
        storedTime = 0L;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStoredTime() {
        return storedTime;
    }
    public void addStoredTime(long timeInMilliseconds){
        storedTime += timeInMilliseconds;
    }

    public long getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(long timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

}
