package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.model.TrainRunDeviationDto;
import com.barnard.javactatraintracker.model.TrainRunFaultyDto;
import com.barnard.javactatraintracker.model.TrainRunLateDto;

import java.util.List;

public interface TrainRunDao {

    List<TrainRunLateDto> getLateTrains();
    List<TrainRunFaultyDto> getFaultyTrains();
    List<TrainRunDeviationDto> getTrainDeviation();

}
