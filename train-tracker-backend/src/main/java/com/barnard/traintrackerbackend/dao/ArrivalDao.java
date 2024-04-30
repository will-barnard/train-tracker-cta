package com.barnard.traintrackerbackend.dao;

import com.barnard.traintrackerbackend.model.Arrival;
import com.barnard.traintrackerbackend.model.TrainRun;

import java.time.LocalDateTime;
import java.util.List;

public interface ArrivalDao {

    void createArrival(Arrival a);
    List<TrainRun> getTrainRunsByDate(int trainRunNum, LocalDateTime start, LocalDateTime end);

}
