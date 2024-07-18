package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Response.ApiResponse;
import com.example.demo.Services.UserService;
import com.example.demo.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody @Valid UserDTO user)
    {
        UserDTO createdUser  =userService.createUser(user);
        ApiResponse<UserDTO> response=new ApiResponse<>(true,"User created success",createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

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
