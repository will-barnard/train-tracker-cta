package com.barnard.javactatraintracker.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TrainRun {

    // CTA train run identifier
    private int trainRunId;
    // DB unique key identifier
    private int runId;
    private List<Arrival> predictions;
    private int countPredictions;
    private LocalDate runDate;
    private LocalDateTime averagePrediction;
    private LocalDateTime arrivalTime;
    private Duration diffActualAverage;
    private boolean wasLate = false;
    private boolean wasFaulty = false;

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

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public LocalDateTime getAveragePrediction() {
        return averagePrediction;
    }

    public void setAveragePrediction(LocalDateTime averagePrediction) {
        this.averagePrediction = averagePrediction;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Duration getDiffActualAverage() {
        return diffActualAverage;
    }

    public void setDiffActualAverage(Duration diffActualAverage) {
        this.diffActualAverage = diffActualAverage;
    }

    public boolean isWasLate() {
        return wasLate;
    }

    public void setWasLate(boolean wasLate) {
        this.wasLate = wasLate;
    }

    public boolean isWasFaulty() {
        return wasFaulty;
    }

    public void setWasFaulty(boolean wasFaulty) {
        this.wasFaulty = wasFaulty;
    }

    public int getCountPredictions() {
        return countPredictions;
    }

    public void setCountPredictions(int countPredictions) {
        this.countPredictions = countPredictions;
    }

    public void calcRunData() {

        this.countPredictions = predictions.size();
        this.runDate = predictions.get(predictions.size() -1).getArrT().toLocalDate();
        this.arrivalTime = predictions.get(predictions.size() -1).getArrT();

        LocalDateTime midnight = predictions.get(predictions.size() -1).getArrT().toLocalDate().atStartOfDay();

        int avgPrd = 0;
        for (Arrival arrival : predictions) {

            avgPrd += (int) Duration.between(midnight, arrival.getArrT()).toSeconds();

            if (arrival.getIsDly() != 0) {
                this.wasLate = true;
            }
            if (arrival.getIsFlt() != 0) {
                this.wasFaulty = true;
            }
        }


        avgPrd /= (predictions.size());
        this.averagePrediction = midnight.plus(Duration.ofSeconds(avgPrd));

        this.diffActualAverage = Duration.between(averagePrediction, arrivalTime);

    }
}
