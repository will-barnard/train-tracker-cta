package com.barnard.javactatraintracker.dao;

import com.barnard.javactatraintracker.exception.DaoException;
import com.barnard.javactatraintracker.model.DatabaseStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcDataDao implements DataDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public DatabaseStats getDatabaseStats() {

        DatabaseStats stats = new DatabaseStats();
        String sql = "select (select count(*) from arrivals) as arrival_count, " +
                "(select count(*) from train_run) as run_count;";

        try {

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            if (rowSet.next()) {
                stats.setArrivalCount(rowSet.getInt("arrival_count"));
                stats.setRunCount(rowSet.getInt("run_count"));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return stats;
    }


}
