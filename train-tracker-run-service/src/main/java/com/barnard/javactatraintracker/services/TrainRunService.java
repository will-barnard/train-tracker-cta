package com.barnard.javactatraintracker.services;

import com.barnard.javactatraintracker.dao.dao;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TrainRunService {

    @Autowired
    private dao dao;

    @Scheduled(cron = "0 0 12 * * *")
    public void createTrainRunRecords() {

        LocalDateTime yesterdayStart = LocalDateTime.now().minusDays(1);
        yesterdayStart = yesterdayStart.toLocalDate().atStartOfDay();
        LocalDateTime yesterdayEnd = LocalDateTime.now().toLocalDate().atStartOfDay().minusSeconds(1);

        for (Integer integer : dao.getListTrainRuns()) {
            System.out.println("Getting TrainRun for run " + integer);
            List<TrainRun> runs = dao.getTrainRunsByDate(integer, yesterdayStart, yesterdayEnd);
            for (TrainRun run : runs) {
                run.calcRunData();
                dao.createTrainRun(run);
            }
        }
    }


}