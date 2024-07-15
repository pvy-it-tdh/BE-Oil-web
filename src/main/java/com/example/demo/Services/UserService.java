package com.example.demo.Services;

import com.example.demo.Reponsitory.UserResponsitory;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserResponsitory userResponsitory;
    public User createUser(User user)
    {
        return userResponsitory.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user= userResponsitory.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }
}
