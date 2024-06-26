package com.barnard.javactatraintracker.controller;

import com.barnard.javactatraintracker.dao.dao;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@CrossOrigin
public class DataController {

    @Autowired
    private dao dao;

    @GetMapping(path = "/reset")
    public void reset() {

        dao.deleteTrainRuns();

        LocalDateTime start = LocalDate.of(2024, Month.MARCH, 6).atStartOfDay();
        start = start.toLocalDate().atStartOfDay();
        System.out.println(start);
        LocalDateTime end = LocalDateTime.now().toLocalDate().atStartOfDay().minusSeconds(1);
        System.out.println(end);


        LocalDateTime starting = LocalDateTime.now();

        for (Integer integer : dao.getListTrainRuns()) {
            System.out.println("Getting TrainRun for run " + integer);
            List<TrainRun> runs = dao.getTrainRunsByDate(integer, 30197, start, end);
            for (TrainRun run : runs) {
                run.calcRunData();
                dao.createTrainRun(run);
            }
            List<TrainRun> runs2 = dao.getTrainRunsByDate(integer, 30198, start, end);
            for (TrainRun run : runs2) {
                run.calcRunData();
                dao.createTrainRun(run);
            }
            System.out.println("Finished run " + integer + " at " + LocalDateTime.now());
        }

        System.out.println("started at " + starting);
        System.out.println("ended at " + LocalDateTime.now());


    }

}
