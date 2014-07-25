package com.springapp.mvc.controller;

import com.springapp.mvc.dto.UserSaveDto;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repo.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserJDBCTemplate userJDBCTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> findAll() {
        return userJDBCTemplate.getUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody User findById(@PathVariable("userId") Integer id) {
        return userJDBCTemplate.getUser(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void addTender(@RequestBody UserSaveDto userSaveDto) {
        userJDBCTemplate.create(userSaveDto.getName());
    }
}
