package com.springapp.mvc.repo;

import com.springapp.mvc.dao.UserDAO;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.springapp.mvc.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate implements UserDAO {
    private static final String BUNDLE_NAME = "userQueries_en";
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private ResourceBundle userRB = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name) {
        jdbcTemplateObject.update(userRB.getString("create"), name);
        System.out.println("Created Record Name = " + name);
    }

    @Override
    public void updateName(Integer id, String name) {
        jdbcTemplateObject.update(userRB.getString("update"), name, id);
        System.out.println("Updated Record with ID = " + id );
    }

    @Override
    public User getUser(Integer id) {
        return jdbcTemplateObject.queryForObject(userRB.getString("findOne"), new Object[]{id}, new UserMapper());
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplateObject.query(userRB.getString("findAll"), new UserMapper());
    }

    @Override
    public void deleteUser(Integer id) {
        jdbcTemplateObject.update(userRB.getString("delete"), id);
        System.out.println("Deleted Record with ID = " + id );
    }
}
