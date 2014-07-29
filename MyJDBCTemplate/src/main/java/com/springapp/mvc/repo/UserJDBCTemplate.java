package com.springapp.mvc.repo;

import com.springapp.mvc.dao.UserDAO;
import com.springapp.mvc.entity.User;

import javax.sql.DataSource;
import java.util.*;

import com.springapp.mvc.service.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class UserJDBCTemplate implements UserDAO {
    private static final String BUNDLE_NAME = "userQueries_en";
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private ResourceBundle userRB = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(String name) {
//        jdbcTemplateObject.update(userRB.getString("create"), name);
        Map namedParameters = new HashMap();
        namedParameters.put("name", name);
        namedParameterJdbcTemplate.update(userRB.getString("create"), namedParameters);
        System.out.println("Created Record Name = " + name);
    }

    @Override
    public void updateName(Integer id, String name) {
//        jdbcTemplateObject.update(userRB.getString("update"), name, id);
        Map namedParameters = new HashMap();
        namedParameters.put("name", name);
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(userRB.getString("update"), namedParameters);
        System.out.println("Updated Record with ID = " + id );
    }

    @Override
    public User getUser(Integer id) {
//        return jdbcTemplateObject.queryForObject(userRB.getString("findOne"), new Object[]{id}, new UserMapper());
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(userRB.getString("findOne"), namedParameters, new UserMapper());
    }

    @Override
    public List<User> getUsers() {
//        return jdbcTemplateObject.query(userRB.getString("findAll"), new UserMapper());
        return jdbcTemplateObject.query(userRB.getString("findAll"), new UserMapper());
    }

    @Override
    public void deleteUser(Integer id) {
//        jdbcTemplateObject.update(userRB.getString("delete"), id);
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(userRB.getString("delete"), namedParameters);
        System.out.println("Deleted Record with ID = " + id );
    }
}
