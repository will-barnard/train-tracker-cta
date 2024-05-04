package com.barnard.javactatraintracker.model;

public class TrainRunDeviationDto {

    private int trainRun;
    private int avgDeviation;
    private int deviationRank;

    public int getTrainRun() {
        return trainRun;
    }

    public void setTrainRun(int trainRun) {
        this.trainRun = trainRun;
    }

    public int getAvgDeviation() {
        return avgDeviation;
    }

    public void setAvgDeviation(int avgDeviation) {
        this.avgDeviation = avgDeviation;
    }

    public int getDeviationRank() {
        return deviationRank;
    }

    public void setDeviationRank(int deviationRank) {
        this.deviationRank = deviationRank;
    }

}
