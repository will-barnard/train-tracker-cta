package com.barnard.javactatraintracker.services;

import com.barnard.javactatraintracker.dao.ArrivalDao;
import com.barnard.javactatraintracker.dao.RunDao;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrainRunService {

    @Autowired
    private ArrivalDao arrivalDao;
    @Autowired
    private RunDao runDao;

    @Scheduled(fixedRate = 86400000)
    public void createTrainRunRecords() {

//        LocalDateTime yesterdayStart = LocalDateTime.now().minusDays(1);
//        yesterdayStart = yesterdayStart.toLocalDate().atStartOfDay();
//        LocalDateTime yesterdayEnd = LocalDateTime.now().toLocalDate().atStartOfDay().minusSeconds(1);
//
//        for (Integer integer : arrivalDao.getListTrainRuns()) {
//            System.out.println("Getting TrainRun for run " + integer);
//            List<TrainRun> runs = arrivalDao.getTrainRunsByDate(integer, yesterdayStart, yesterdayEnd);
//            for (TrainRun run : runs) {
//                run.calcRunData();
//                runDao.createTrainRun(run);
//            }
//
//        }

    }


}
