package com.barnard.javactatraintracker.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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



    train run chance of being late

    outlier trains

     */

    private List<TrainRunAggregateDto> runList;
    private List<TrainRunLateDto> lateRuns;
    private List<TrainRunFaultyDto> faultyRuns;
    private List<TrainRunDeviationDto> deviationRuns;
    private int mostLate;
    private int leastLate;
    private int mostFaulty;
    private int leastFaulty;
    private int mostDeviant;
    private int leastDeviant;

    public List<TrainRunAggregateDto> getRunList() {
        return runList;
    }

    public void setRunList(List<TrainRunAggregateDto> runList) {
        this.runList = runList;
    }

    public int getMostLate() {
        return mostLate;
    }

    public int getLeastLate() {
        return leastLate;
    }

    public int getMostFaulty() {
        return mostFaulty;
    }

    public int getLeastFaulty() {
        return leastFaulty;
    }

    public int getMostDeviant() {
        return mostDeviant;
    }

    public int getLeastDeviant() {
        return leastDeviant;
    }

    public void calculateData() {

        int mostLateRun = 0;
        double mostLateAmount = 0;
        int leastLateRun = 0;
        double leastLateAmount = 100;
        int mostFaultyRun = 0;
        double mostFaultyAmount = 0;
        int leastFaultyRun = 0;
        double leastFaultyAmount = 100;
        int mostDeviantRun = 0;
        int mostDeviantAmount = 0;
        int leastDeviantRun = 0;
        int leastDeviantAmount = 10000;

        for (TrainRunAggregateDto run : this.runList) {

            if (run.getDataSize() < 100) {
                run.setOutlier(true);
            }
            {
                BigDecimal latePercent = new BigDecimal(run.getCountLate());
                BigDecimal div = latePercent.divide(new BigDecimal(run.getDataSize()), new MathContext(4));
                BigDecimal percent = div.multiply(new BigDecimal(100));
                BigDecimal res = percent.setScale(2, RoundingMode.CEILING);
                run.setLatePercent(res.doubleValue());
            }
            {
                BigDecimal faultyPercent = new BigDecimal(run.getCountFaulty());
                BigDecimal div = faultyPercent.divide(new BigDecimal(run.getDataSize()), new MathContext(4));
                BigDecimal percent = div.multiply(new BigDecimal(100));
                BigDecimal res = percent.setScale(2, RoundingMode.CEILING);
                run.setFaultyPercent(res.doubleValue());
            }
            if (!run.isOutlier()) {

                if (run.getLatePercent() > mostLateAmount) {
                    mostLateRun = run.getTrainRun();
                    mostLateAmount = run.getLatePercent();
                }
                if (run.getLatePercent() < leastLateAmount) {
                    leastLateRun = run.getTrainRun();
                    leastLateAmount = run.getLatePercent();
                }
                if (run.getFaultyPercent() > mostFaultyAmount) {
                    mostFaultyRun = run.getTrainRun();
                    mostFaultyAmount = run.getFaultyPercent();
                }
                if (run.getFaultyPercent() < leastFaultyAmount) {
                    leastFaultyRun = run.getTrainRun();
                    leastFaultyAmount = run.getFaultyPercent();
                }
                if (run.getAvgDeviation() > mostDeviantAmount) {
                    mostDeviantRun = run.getTrainRun();
                    mostDeviantAmount = run.getAvgDeviation();
                }
                if (run.getAvgDeviation() < leastDeviantAmount) {
                    leastDeviantRun = run.getTrainRun();
                    leastDeviantAmount = run.getAvgDeviation();
                }

            }
        }
        this.mostLate = mostLateRun;
        this.leastLate = leastLateRun;
        this.mostFaulty = mostFaultyRun;
        this.leastFaulty = leastFaultyRun;
        this.mostDeviant = mostDeviantRun;
        this.leastDeviant = leastDeviantRun;
    }
}
