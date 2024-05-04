package com.barnard.javactatraintracker.model;

public class TrainRunFaultyDto {

    private int trainRun;
    private int countFaulty;
    private int faultyRank;

    public int getTrainRun() {
        return trainRun;
    }

    public void setTrainRun(int trainRun) {
        this.trainRun = trainRun;
    }

    public int getCountFaulty() {
        return countFaulty;
    }

    public void setCountFaulty(int countFaulty) {
        this.countFaulty = countFaulty;
    }

    public int getFaultyRank() {
        return faultyRank;
    }

    public void setFaultyRank(int faultyRank) {
        this.faultyRank = faultyRank;
    }

}
