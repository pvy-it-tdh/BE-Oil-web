package com.example.demo.Services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userResponsitory;
    public User createUser(UserDTO userDTO)
    {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
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
