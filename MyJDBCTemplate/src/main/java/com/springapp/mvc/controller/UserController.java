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
    public @ResponseBody List<User> addTender(@RequestBody UserSaveDto userSaveDto) {
        userJDBCTemplate.create(userSaveDto.getName());
        return userJDBCTemplate.getUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public @ResponseBody User update(@RequestBody UserSaveDto userSaveDto, @PathVariable("userId") Integer id) {
        userJDBCTemplate.updateName(id, userSaveDto.getName());
        return userJDBCTemplate.getUser(id);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public @ResponseBody List<User> deleteById(@PathVariable("userId") Integer id) {
        userJDBCTemplate.deleteUser(id);
        return userJDBCTemplate.getUsers();
    }
}
