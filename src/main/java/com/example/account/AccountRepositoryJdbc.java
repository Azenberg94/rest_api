package com.example.account;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Az on 02/03/2017.
 */
@Repository
public class AccountRepositoryJdbc extends JdbcDaoSupport{

    public AccountRepositoryJdbc(DataSource dataSource){
        setDataSource(dataSource);
    }

    public AccountEntity findByName(String name){
        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        return jdbcTemplate.queryForObject("select * from account where name = ?", this::map, name);
    }

    public AccountEntity map(ResultSet rs, int rowNumber) throws SQLException{
        return AccountEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .type(Type.valueOf(rs.getString("type")))
                .build();
    }
}
