package com.springapp.mvc.controller;

import com.springapp.mvc.dto.UserSaveDto;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repo.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserJDBCTemplate userJDBCTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> findAll() {
        logger.info("==================== Founded all users ======================");
        return userJDBCTemplate.getUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody User findById(@PathVariable("userId") Integer id) {
        logger.info("==================== Founded only one user ======================");
        return userJDBCTemplate.getUser(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<User> addUser(@RequestBody UserSaveDto userSaveDto) {
        userJDBCTemplate.create(userSaveDto.getName());
        logger.info("==================== Added user ======================");
        return userJDBCTemplate.getUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public @ResponseBody User update(@RequestBody UserSaveDto userSaveDto, @PathVariable("userId") Integer id) {
        userJDBCTemplate.updateName(id, userSaveDto.getName());
        logger.info("==================== Updated user ======================");
        return userJDBCTemplate.getUser(id);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public @ResponseBody List<User> deleteById(@PathVariable("userId") Integer id) {
        userJDBCTemplate.deleteUser(id);
        logger.info("==================== The user was deleted ======================");
        return userJDBCTemplate.getUsers();
    }
}
