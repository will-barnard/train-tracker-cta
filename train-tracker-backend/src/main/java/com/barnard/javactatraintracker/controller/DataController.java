package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.dao.ArrivalDao;
import com.barnard.javactatraintracker.dao.DataDao;
import com.barnard.javactatraintracker.dao.TrainRunDao;
import com.barnard.javactatraintracker.model.AggregateDataDto;
import com.barnard.javactatraintracker.model.DatabaseStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DataController {

    @Autowired
    private ArrivalDao arrivalDao;
    @Autowired
    private TrainRunDao trainRunDao;
    @Autowired
    private DataDao dataDao;

    @GetMapping(path = "lsdata")
    public AggregateDataDto getTrainRunData() {
        AggregateDataDto data = new AggregateDataDto();
        data.setRunList(trainRunDao.getTrainRunData());
        data.calculateData();
        return data;
    }

    @GetMapping(path = "stats")
    public DatabaseStats getDatabaseStats() {
        return dataDao.getDatabaseStats();
    }

}
