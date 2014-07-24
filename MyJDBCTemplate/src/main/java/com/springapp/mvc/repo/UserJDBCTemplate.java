package com.springapp.mvc.repo;

import com.springapp.mvc.dao.UserDAO;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;

import javax.sql.DataSource;
import java.util.List;

import com.springapp.mvc.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Role role) {
        jdbcTemplateObject.update("insert into User (name) values (?)", name);
        System.out.println("Created Record Name = " + name);
    }

    @Override
    public void updateName(Integer id, String name) {
        jdbcTemplateObject.update("update User set name = ? where id = ?", name, id);
        System.out.println("Updated Record with ID = " + id );
    }

    @Override
    public User getUser(Integer id) {
        User user = jdbcTemplateObject.queryForObject("select * from User where id = ?", new Object[]{id}, new UserMapper());
        return user;
    }

    @Override
    public List<User> getUsers() {
        List users = jdbcTemplateObject.query("select * from User", new UserMapper());
        return users;
    }

    @Override
    public void deleteUser(Integer id) {
        String SQL = "delete from User where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }
}
