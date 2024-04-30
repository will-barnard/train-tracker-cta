package com.barnard.traintrackerbackend.model;

import java.util.List;

public class TrainRun {

    private int trainRunId;
    private List<Arrival> predictions;

    public int getTrainRunId() {
        return trainRunId;
    }

    public void setTrainRunId(int trainRun) {
        this.trainRunId = trainRun;
    }

    public List<Arrival> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Arrival> predictions) {
        this.predictions = predictions;
    }

    public void addArrival(Arrival arrival) {
        predictions.add(arrival);
    }

}
