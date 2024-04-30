package com.barnard.traintrackerbackend.controller;

import com.barnard.traintrackerbackend.dao.ArrivalDao;
import com.barnard.traintrackerbackend.model.RunRequestDto;
import com.barnard.traintrackerbackend.model.TrainRun;
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
        return arrivalDao.getTrainRunsByDate(dto.getTrainRun(), dto.getStart(), dto.getEnd());
    }

}
