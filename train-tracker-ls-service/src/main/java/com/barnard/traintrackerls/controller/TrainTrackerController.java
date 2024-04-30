package com.barnard.traintrackerls.controller;

import com.barnard.traintrackerls.model.ArrivalsParams;
import com.barnard.traintrackerls.model.FollowThisTrainParams;
import com.barnard.traintrackerls.model.LocationsParams;
import com.barnard.traintrackerls.services.GetArrivalService;
import com.barnard.traintrackerls.services.TrainTrackerService;
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
    GetArrivalService getArrivalService;
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

}