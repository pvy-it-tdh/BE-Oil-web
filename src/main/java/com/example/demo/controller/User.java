package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {
    @GetMapping(value = "/api/phucvy")
    public String getAllUser(){
        return "hello";
    }
}
