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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDao implements dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TrainRun> getTrainRunsByDate(int trainRunNum, LocalDateTime start, LocalDateTime end) {
        List<TrainRun> trainRuns = new ArrayList<>();

        String sql = "select * from arrivals " +
                "where prediction_time between ? and ? " +
                "and train_run = ? " +
                "order by prediction_time asc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, start, end, trainRunNum);
            while(rowSet.next()) {
                Arrival arrival = mapRowToArrival(rowSet);
                if (trainRuns.isEmpty()) {
                    System.out.println("creating first run for " + arrival.getRn());
                    TrainRun trainRun = new TrainRun();
                    trainRun.setTrainRunId(arrival.getRn());
                    List<Arrival> arrivals = new ArrayList<>();
                    trainRun.setPredictions(arrivals);
                    trainRun.addArrival(arrival);

                    trainRuns.add(trainRun);
                } else {
                    int duration = Math.abs((int) Duration.between(arrival.getArrT(),
                            trainRuns.get(trainRuns.size() - 1).getPredictions().get(trainRuns.get(trainRuns.size() - 1).getPredictions().size() - 1).getArrT()).toSeconds());
                    if (duration < 7200) {
                        trainRuns.get(trainRuns.size() - 1).addArrival(arrival);
                    } else {
                        System.out.println("creating new run for " + arrival.getRn());
                        TrainRun trainRun = new TrainRun();
                        trainRun.setTrainRunId(arrival.getRn());
                        List<Arrival> arrivals = new ArrayList<>();
                        trainRun.setPredictions(arrivals);
                        trainRun.addArrival(arrival);

                        trainRuns.add(trainRun);
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return trainRuns;
    }

    @Override
    public List<TrainRun> getTrainRunsByDate(int trainRunNum, int dest, LocalDateTime start, LocalDateTime end) {
        List<TrainRun> trainRuns = new ArrayList<>();

        String sql = "select * from arrivals " +
                "where prediction_time between ? and ? " +
                "and train_run = ? " +
                "and stop_id = ? " +
                "order by prediction_time asc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, start, end, trainRunNum, dest);
            while(rowSet.next()) {
                Arrival arrival = mapRowToArrival(rowSet);
                if (trainRuns.isEmpty()) {
                    System.out.println("creating first run for " + arrival.getRn());
                    TrainRun trainRun = new TrainRun();
                    trainRun.setTrainRunId(arrival.getRn());
                    List<Arrival> arrivals = new ArrayList<>();
                    trainRun.setPredictions(arrivals);
                    trainRun.addArrival(arrival);

                    trainRuns.add(trainRun);
                } else {
                    int duration = Math.abs((int) Duration.between(arrival.getArrT(),
                            trainRuns.get(trainRuns.size() - 1).getPredictions().get(trainRuns.get(trainRuns.size() - 1).getPredictions().size() - 1).getArrT()).toSeconds());
                    if (duration < 7200) {
                        trainRuns.get(trainRuns.size() - 1).addArrival(arrival);
                    } else {
                        System.out.println("creating new run for " + arrival.getRn());
                        TrainRun trainRun = new TrainRun();
                        trainRun.setTrainRunId(arrival.getRn());
                        List<Arrival> arrivals = new ArrayList<>();
                        trainRun.setPredictions(arrivals);
                        trainRun.addArrival(arrival);

                        trainRuns.add(trainRun);
                    }
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return trainRuns;
    }

    @Override
    public List<Integer> getListTrainRuns() {

        List<Integer> result = new ArrayList<>();
        String sql = "SELECT train_run FROM arrivals " +
                "GROUP BY train_run;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()) {
                result.add(rowSet.getInt("train_run"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return result;
    }

    @Override
    public void createTrainRun(TrainRun run) {

        String sql = "INSERT INTO train_run (run, run_date, average_prediction, arrival_time, difference_actual_average, was_late, was_faulty, prediction_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING run_id;";

        String sql2 = "UPDATE arrivals SET train_run_id = ? " +
                "WHERE ";
        String concat = "";
        String arrivalIdSql = "arrival_id = ";
        for (int i = 0; i < run.getPredictions().size(); i++) {
            concat += arrivalIdSql + run.getPredictions().get(i).getArrivalId();
            if (i == run.getPredictions().size() - 1) {
                concat += ";";
            } else {
                concat += " OR ";
            }
        }
        sql2 += concat;


        try {
            Integer runId = jdbcTemplate.queryForObject(sql, Integer.class, run.getTrainRunId(), run.getRunDate(), run.getAveragePrediction(), run.getArrivalTime(), run.getDiffActualAverage().toSeconds(), run.isWasLate(), run.isWasFaulty(), run.getCountPredictions());
            jdbcTemplate.update(sql2, runId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public void deleteTrainRuns() {

        String sql = "ALTER TABLE arrivals DROP CONSTRAINT arrivals_train_run_id_fkey; " +
                "ALTER TABLE arrivals DROP COLUMN train_run_id; " +
                "DELETE FROM train_run; " +
                "ALTER TABLE arrivals ADD train_run_id int; " +
                "ALTER TABLE arrivals ADD CONSTRAINT arrivals_train_run_id_fkey FOREIGN KEY (train_run_id) REFERENCES train_run(run_id);";

        try {
            jdbcTemplate.update(sql);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Arrival mapRowToArrival(SqlRowSet rs) {
        Arrival arrival = new Arrival();

        arrival.setArrivalId(rs.getInt("arrival_id"));
        arrival.setStaId(rs.getInt("station_id"));
        arrival.setStpId(rs.getInt("stop_id"));
        arrival.setStaNm(rs.getString("station_name"));
        arrival.setStpDe(rs.getString("stop_description"));
        arrival.setRn(rs.getInt("train_run"));
        arrival.setRt(rs.getString("train_route"));
        arrival.setDestSt(rs.getInt("destination_stop"));
        arrival.setDestNm(rs.getString("destination_name"));
        arrival.setTrDr(rs.getInt("train_direction"));
        arrival.setPrdt(rs.getTimestamp("prediction_time").toLocalDateTime());
        arrival.setArrT(rs.getTimestamp("arrival_prediction").toLocalDateTime());
        arrival.setIsApp(rs.getInt("is_app"));
        arrival.setIsSch(rs.getInt("is_sch"));
        arrival.setIsDly(rs.getInt("is_delay"));
        arrival.setIsFlt(rs.getInt("is_faulty"));
        arrival.setLat(rs.getDouble("lat"));
        arrival.setLon(rs.getDouble("lon"));
        arrival.setTrainRunId(rs.getInt("train_run_id"));

        return arrival;
    }
}
