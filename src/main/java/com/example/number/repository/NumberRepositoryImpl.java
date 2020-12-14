package com.example.number.repository;

import com.example.number.model.NumberTale;
import com.example.number.util.NumberAPI;
import com.example.number.util.NumberTaleUtil;
import com.example.number.util.exception.WrongConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class NumberRepositoryImpl implements NumberRepository {

    private static final RowMapper<NumberTale> ROW_MAPPER = BeanPropertyRowMapper.newInstance(NumberTale.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertNumberTale;

    public NumberRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.insertNumberTale = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("numbers")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NumberTale> getIfExsist(int number) {
        List<NumberTale> numbers = jdbcTemplate.query(
                "SELECT * FROM numbers WHERE number = ?", ROW_MAPPER, number);
        return numbers;
    }

    @Override
    @Transactional
    public NumberTale get(int number) {
        List<NumberTale> numberTaleList = getIfExsist(number);
        //if number doesnt exit in database, trying to retrieve from http://numbersapi.com
        if (numberTaleList.size() == 0) {
            try {
                LocalTime startTime = LocalTime.now();
                String description = NumberAPI.fetchNumber(number);
                LocalTime endTime = LocalTime.now();
                save(number, description, (int) Duration.between(startTime, endTime).toMillis());
                return DataAccessUtils.singleResult(getIfExsist(number));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongConnectionException wrongConn) {
                increaseErrorConn();
            }
        } else {
            update(number);
        }
        return DataAccessUtils.singleResult(getIfExsist(number));
    }

    @Override
    public List<NumberTale> getTopNumbers() {
        return jdbcTemplate.query(
                "SELECT * FROM numbers ORDER BY request DESC LIMIT 10", ROW_MAPPER);
    }


    @Transactional
    public void save(int number, String description, int latency) {
        NumberTale newNumberTale = NumberTaleUtil.createNew(number, description, latency);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", newNumberTale.getId())
                .addValue("description", newNumberTale.getDescription())
                .addValue("request", newNumberTale.getRequest())
                .addValue("number", newNumberTale.getNumber())
                .addValue("latency", newNumberTale.getLatency());
        Number newId = insertNumberTale.executeAndReturnKey(map);
        newNumberTale.setId(newId.intValue());
    }

    @Transactional
    public void update(int number) {
        jdbcTemplate.update(
                "UPDATE numbers SET request = request +1 WHERE number=?", number);
    }

    @Transactional
    public void increaseErrorConn() {
        jdbcTemplate.update(
                "UPDATE errors SET error = error +1 WHERE id=?", 500);
    }

    public int getNumberSuccessInsert() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM numbers", Integer.class);
    }

    public int getNumberErrorInsert() {
        return jdbcTemplate
                .queryForObject("SELECT error FROM errors WHERE id=?", Integer.class, 500);
    }

    @Override
    public int getAverageLatency() {
        return jdbcTemplate.queryForObject("SELECT AVG(latency) FROM numbers", Integer.class);
    }

    @Override
    public double getAverageSuccessRate() {
        int numberSuccessInsert = getNumberSuccessInsert();
        int numberErrorInsert = getNumberErrorInsert();
        return (double) numberSuccessInsert / (((double) (numberSuccessInsert + numberErrorInsert)) / 100);
    }
}
