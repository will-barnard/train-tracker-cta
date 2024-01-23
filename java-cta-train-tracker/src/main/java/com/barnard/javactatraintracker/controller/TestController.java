package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.service.TrainTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private TrainTrackerService trainTrackerService;

    @GetMapping(path="/test")
    public Object getTrain() {
        return trainTrackerService.getTrainStatus();
    }

}
