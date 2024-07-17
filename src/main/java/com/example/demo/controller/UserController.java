package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Services.UserService;
import com.example.demo.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
     User createUser(@RequestBody @Valid UserDTO user)
    {
        return userService.createUser(user);
    }
    @GetMapping("/{userId}")
    User  getUserById(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }
    @PutMapping("/{userId}")
    User updateUser(@PathVariable Long userId,@RequestBody UserUpdate userUpdate)
    {
        return userService.UserUpdate(userId,userUpdate);
    }

}
