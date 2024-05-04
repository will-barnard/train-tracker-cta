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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcArrivalDao implements ArrivalDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createArrival(Arrival a) {

        String sql = "INSERT INTO arrivals (station_id, stop_id, station_name, stop_description," +
                " train_run, train_route, destination_stop, destination_name, train_direction, " +
                "prediction_time, arrival_prediction, is_app, is_sch, is_delay, is_faulty, lat, lon) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {

            int rowsAffected = jdbcTemplate.update(sql, a.getStaId(), a.getStpId(), a.getStaNm(), a.getStpDe(), a.getRn(), a.getRt(), a.getDestSt(), a.getDestNm(),
                    a.getTrDr(), a.getPrdt(), a.getArrT(), a.getIsApp(), a.getIsSch(), a.getIsDly(), a.getIsFlt(), a.getLat(), a.getLon());

            if (rowsAffected == 0) {
                throw new DaoException();
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public List<Arrival> getArrivalsByTrainRun(int trainRunId) {
        List<Arrival> arrivals = new ArrayList<>();

        String sql = "select * from arrivals " +
                "where train_run_id = ? " +
                "order by prediction_time asc;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, trainRunId);
            while(rowSet.next()) {
                arrivals.add(mapRowToArrival(rowSet));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return arrivals;
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

        return arrival;
    }
}
