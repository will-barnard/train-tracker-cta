package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.exception.DaoException;
import com.barnard.javactatraintracker.model.Arrival;
import com.barnard.javactatraintracker.model.TrainRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcRunDao implements RunDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createTrainRun(TrainRun run) {

        String sql = "INSERT INTO train_run (run, run_date, average_prediction, arrival_time, difference_actual_average, was_late, was_faulty) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING run_id;";
        String sql2 = "INSERT INTO run_arrival (run_id, arrival_id) " +
                "VALUES (?, ?);";

        try {
            Integer runId = jdbcTemplate.queryForObject(sql, Integer.class, run.getTrainRunId(), run.getRunDate(), run.getAveragePrediction(), run.getArrivalTime(), run.getDiffActualAverage().toSeconds(), run.isWasLate(), run.isWasFaulty());
            for (Arrival arrival : run.getPredictions()) {
                jdbcTemplate.update(sql2, runId, arrival.getArrivalId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }


}
