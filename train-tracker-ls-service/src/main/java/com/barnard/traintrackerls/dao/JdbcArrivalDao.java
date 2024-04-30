package com.barnard.traintrackerls.dao;

import com.barnard.traintrackerls.exception.DaoException;
import com.barnard.traintrackerls.model.Arrival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
