package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.model.TrainRun;

import java.util.List;

public interface RunDao {

    void createTrainRun(TrainRun run);
    void deleteTrainRuns();


}
