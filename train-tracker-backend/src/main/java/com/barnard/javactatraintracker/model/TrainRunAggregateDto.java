package com.barnard.javactatraintracker.model;

public class TrainRunAggregateDto {

    private int trainRun;
    private int dataSize;
    private int countLate;
    private int countFaulty;
    private int avgDeviation;

    // calculated variables
    private double latePercent;
    private double faultyPercent;
    private boolean isOutlier = false;

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

    public int getCountFaulty() {
        return countFaulty;
    }

    public void setCountFaulty(int countFaulty) {
        this.countFaulty = countFaulty;
    }

    public int getAvgDeviation() {
        return avgDeviation;
    }

    public void setAvgDeviation(int avgDeviation) {
        this.avgDeviation = avgDeviation;
    }

    public double getLatePercent() {
        return latePercent;
    }

    public void setLatePercent(double latePercent) {
        this.latePercent = latePercent;
    }

    public double getFaultyPercent() {
        return faultyPercent;
    }

    public void setFaultyPercent(double faultyPercent) {
        this.faultyPercent = faultyPercent;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public boolean isOutlier() {
        return isOutlier;
    }

    public void setOutlier(boolean outlier) {
        isOutlier = outlier;
    }
}
