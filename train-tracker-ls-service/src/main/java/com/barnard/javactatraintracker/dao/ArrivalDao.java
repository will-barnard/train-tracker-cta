package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.model.Arrival;
import com.barnard.javactatraintracker.model.TrainRun;

import java.time.LocalDateTime;
import java.util.List;

public interface ArrivalDao {

    void createArrival(Arrival a);
    List<TrainRun> getTrainRunsByDate(int trainRunNum, LocalDateTime start, LocalDateTime end);

}
