package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.model.TrainRun;

import java.time.LocalDateTime;
import java.util.List;

public interface dao {

    List<TrainRun> getTrainRunsByDate(int trainRunNum, LocalDateTime start, LocalDateTime end);
    List<TrainRun> getTrainRunsByDate(int trainRunNum, int dest, LocalDateTime start, LocalDateTime end);
    List<Integer> getListTrainRuns();
    void createTrainRun(TrainRun run);
    void deleteTrainRuns();



}
