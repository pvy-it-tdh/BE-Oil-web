package com.example.demo.Services;

import com.example.demo.Reponsitory.UserResponsitory;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserResponsitory userResponsitory;
    public User createUser(User user)
    {
        return userResponsitory.save(user);
    }
}
