package com.company;

public class process {

    public String name;
    public int ArrivalTime;
    public int ExecutionTime;
    public int waitingTime;
    public int turnAroundTime;
    public int extime;
    public boolean isDone = false;
    public int startingTime;

    public process(String name, int ArrivalTime, int ExecutionTime) {
        this.name = name;
        this.ArrivalTime = ArrivalTime;
        this.ExecutionTime = ExecutionTime;
        extime = ExecutionTime;
        startingTime = -1;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public void setStartingTime(int startingTime) {
        if(this.startingTime == -1){
            this.startingTime = startingTime;
        }
    }

    public int getStartingTime() {
        return startingTime;
    }
}
