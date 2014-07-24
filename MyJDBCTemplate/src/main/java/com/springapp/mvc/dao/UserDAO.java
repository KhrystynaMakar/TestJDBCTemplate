package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {
    public void setDataSource(DataSource dataSource);
    public void create(String name, Role role);
    public void updateName(Integer id, String name);
    public User getUser(Integer id);
    public List<User> getUsers();
    public void deleteUser(Integer id);
}
