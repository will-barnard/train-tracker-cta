package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.dao.ArrivalDao;
import com.barnard.javactatraintracker.dao.RunDao;
import com.barnard.javactatraintracker.model.RunRequestDto;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ArrivalDataController {

    // this controller will generate the train_run database table from the arrivals table

    @Autowired
    private ArrivalDao arrivalDao;
    @Autowired
    private RunDao runDao;
//
//    @GetMapping(path = "/generate")
//    public void trainRunTest() {
//
//        LocalDateTime start = LocalDateTime.now().minusDays(57);
//        start = start.toLocalDate().atStartOfDay();
//        System.out.println(start);
//        LocalDateTime end = LocalDateTime.now().toLocalDate().atStartOfDay().minusSeconds(1);
//        System.out.println(end);
//
//
//        LocalDateTime starting = LocalDateTime.now();
//
//        for (Integer integer : arrivalDao.getListTrainRuns()) {
//            System.out.println("Getting TrainRun for run " + integer);
//            List<TrainRun> runs = arrivalDao.getTrainRunsByDate(integer, start, end);
//            for (TrainRun run : runs) {
//                run.calcRunData();
//                runDao.createTrainRun(run);
//            }
//            System.out.println("Finished run " + integer + " at " + LocalDateTime.now());
//        }
//
//        System.out.println("started at " + starting);
//        System.out.println("ended at " + LocalDateTime.now());
//    }

}
