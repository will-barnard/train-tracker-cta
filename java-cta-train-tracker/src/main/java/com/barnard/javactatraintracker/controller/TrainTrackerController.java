package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.model.ArrivalResult;
import com.barnard.javactatraintracker.model.ArrivalsParams;
import com.barnard.javactatraintracker.model.FollowThisTrainParams;
import com.barnard.javactatraintracker.model.LocationsParams;
import com.barnard.javactatraintracker.services.DataCollectionService;
import com.barnard.javactatraintracker.services.TrainTrackerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin
public class TrainTrackerController {

    @Autowired
    private TrainTrackerService trainTrackerService;
    @Autowired
    DataCollectionService dataCollectionService;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(path="/arrivals")
    public Object getArrivals(@RequestBody ArrivalsParams arrivalsParams) {
        return trainTrackerService.getArrivals(arrivalsParams);
    }

    @PostMapping(path="/follow")
    public Object getFollowThisTrain(@RequestBody FollowThisTrainParams followThisTrainParams) {
        return trainTrackerService.getFollowThisTrain(followThisTrainParams);
    }

    @PostMapping(path="/locations")
    public Object getLocations(@RequestBody LocationsParams locationsParams) {
        return trainTrackerService.getLocations(locationsParams);
    }

    @GetMapping(path="/testjson")
    public String testJsonParse() {

        ArrivalResult testArrival = dataCollectionService.getTest();
        System.out.println(testArrival);
        System.out.println(testArrival.getCtatt().getTmst());
        System.out.println((testArrival.getCtatt().getEta().get(0).getArrT()).getClass());

        return "done";
    }

}