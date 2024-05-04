package com.barnard.javactatraintracker.model;

import java.util.List;

public class AggregateDataDto {

    /*

    new table for each train run # and update data daily?
    take get duration in seconds since previous day midnight? check if predictions are the same day

    most often late train
        -how much more late than 2nd most
        - % chance for it to be late

    latest train

    most faulty train

    most on time train
        - exclude outliers

    train run average/estimated arrival times
        - calculate this as a service?

    train run chance of being late

    outlier trains

     */

    private List<TrainRunLateDto> lateRuns;
    private int mostLate;
    private List<TrainRunFaultyDto> faultyRuns;
    private int mostFaulty;
    private List<TrainRunDeviationDto> deviationRuns;
    private int mostDeviant;

    public List<TrainRunLateDto> getLateRuns() {
        return lateRuns;
    }

    public void setLateRuns(List<TrainRunLateDto> lateRuns) {
        this.lateRuns = lateRuns;
    }

    public int getMostLate() {
        return mostLate;
    }

    public void setMostLate(int mostLate) {
        this.mostLate = mostLate;
    }

    public List<TrainRunFaultyDto> getFaultyRuns() {
        return faultyRuns;
    }

    public void setFaultyRuns(List<TrainRunFaultyDto> faultyRuns) {
        this.faultyRuns = faultyRuns;
    }

    public int getMostFaulty() {
        return mostFaulty;
    }

    public void setMostFaulty(int mostFaulty) {
        this.mostFaulty = mostFaulty;
    }

    public List<TrainRunDeviationDto> getDeviationRuns() {
        return deviationRuns;
    }

    public void setDeviationRuns(List<TrainRunDeviationDto> deviationRuns) {
        this.deviationRuns = deviationRuns;
    }

    public int getMostDeviant() {
        return mostDeviant;
    }

    public void setMostDeviant(int mostDeviant) {
        this.mostDeviant = mostDeviant;
    }
}
