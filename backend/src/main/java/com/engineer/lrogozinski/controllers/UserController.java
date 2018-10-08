package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<Account> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Account getOne(@PathVariable(value = "id") Integer id){
        return userService.findById(id);
    }

   /* @RequestMapping(value="/signup", method = RequestMethod.POST)
    public Account saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }*/

}