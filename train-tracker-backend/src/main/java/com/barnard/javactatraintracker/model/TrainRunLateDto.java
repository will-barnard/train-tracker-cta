package com.barnard.javactatraintracker.model;

public class TrainRunLateDto {

    private int trainRun;
    private int countLate;
    private int lateRank;

    public int getTrainRun() {
        return trainRun;
    }

    public void setTrainRun(int trainRun) {
        this.trainRun = trainRun;
    }

    public int getCountLate() {
        return countLate;
    }

    public void setCountLate(int countLate) {
        this.countLate = countLate;
    }

    public int getLateRank() {
        return lateRank;
    }

    public void setLateRank(int lateRank) {
        this.lateRank = lateRank;
    }

}
