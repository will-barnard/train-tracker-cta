package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.exception.DaoException;
import com.barnard.javactatraintracker.model.TrainRunAggregateDto;
import com.barnard.javactatraintracker.model.TrainRunDeviationDto;
import com.barnard.javactatraintracker.model.TrainRunFaultyDto;
import com.barnard.javactatraintracker.model.TrainRunLateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrainRunDao implements TrainRunDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TrainRunLateDto> getLateTrains() {

        List<TrainRunLateDto> trainRunLate = new ArrayList<>();
        String sql = "select run, count(CASE WHEN was_late THEN 1 END) as count_late from train_run " +
                "where was_late = true " +
                "group by run " +
                "order by count_late desc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            int count = 1;
            while (rowSet.next()) {
                TrainRunLateDto run = new TrainRunLateDto();
                run.setTrainRun(rowSet.getInt("run"));
                run.setCountLate(rowSet.getInt("count_late"));
                run.setLateRank(count);
                count++;
                trainRunLate.add(run);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return trainRunLate;
    }

    @Override
    public List<TrainRunFaultyDto> getFaultyTrains() {

        List<TrainRunFaultyDto> trainRunFaulty = new ArrayList<>();
        String sql = "select run, count(CASE WHEN was_faulty THEN 1 END) as count_faulty from train_run " +
                "where was_faulty = true " +
                "group by run " +
                "order by count_faulty desc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            int count = 1;
            while (rowSet.next()) {
                TrainRunFaultyDto run = new TrainRunFaultyDto();
                run.setTrainRun(rowSet.getInt("run"));
                run.setCountFaulty(rowSet.getInt("count_faulty"));
                run.setFaultyRank(count);
                count++;
                trainRunFaulty.add(run);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return trainRunFaulty;
    }

    @Override
    public List<TrainRunDeviationDto> getTrainDeviation() {

        List<TrainRunDeviationDto> trainRunDeviation = new ArrayList<>();
        String sql = "select run, avg(abs(difference_actual_average)) as avg_dev " +
                "from train_run " +
                "group by run " +
                "order by avg_dev desc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            int count = 1;
            while (rowSet.next()) {
                TrainRunDeviationDto run = new TrainRunDeviationDto();
                run.setTrainRun(rowSet.getInt("run"));
                run.setAvgDeviation((int) Math.floor(rowSet.getDouble("avg_dev")));
                run.setDeviationRank(count);
                count++;
                trainRunDeviation.add(run);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return trainRunDeviation;
    }

    @Override
    public List<TrainRunAggregateDto> getTrainRunData() {

        List<TrainRunAggregateDto> trainRunData = new ArrayList<>();
        String sql = "select run, count(CASE WHEN was_late THEN 1 END) as count_late, " +
                "count(CASE WHEN was_faulty THEN 1 END) as count_faulty, " +
                "avg(abs(difference_actual_average)) as avg_dev, " +
                "count(*) as data_size from train_run " +
                "group by run " +
                "order by run asc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while (rowSet.next()) {
                TrainRunAggregateDto run = new TrainRunAggregateDto();
                run.setTrainRun(rowSet.getInt("run"));
                run.setCountLate(rowSet.getInt("count_late"));
                run.setCountFaulty(rowSet.getInt("count_faulty"));
                run.setAvgDeviation((int) Math.floor(rowSet.getDouble("avg_dev")));
                run.setDataSize(rowSet.getInt("data_size"));
                trainRunData.add(run);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return trainRunData;
    }
}
