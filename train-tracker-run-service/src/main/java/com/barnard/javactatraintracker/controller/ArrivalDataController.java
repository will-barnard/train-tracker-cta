package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.dao.ArrivalDao;
import com.barnard.javactatraintracker.model.RunRequestDto;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ArrivalDataController {

    // todo endpoint for pulling arrival data to frontend

    @Autowired
    private ArrivalDao arrivalDao;

    @GetMapping(path = "/run")
    public List<TrainRun> getPredictionsByRun(@RequestBody RunRequestDto dto) {
        List<TrainRun> result = arrivalDao.getTrainRunsByDate(dto.getTrainRun(), dto.getStart(), dto.getEnd());
        return result;
    }

}
