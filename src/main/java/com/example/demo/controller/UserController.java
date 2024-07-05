package com.example.demo.controller;

import com.example.demo.Services.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }


}
