package com.example.demo.Services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    public User createUser(UserDTO userDTO)
    {
        User user = new User();
        if (userRepository.existsByUsername(userDTO.getUsername()))
        {
            throw new  RuntimeException("User exists");
        }
        user.setUsername(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user= userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user);
    }
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User UserUpdate(Long userId ,UserUpdate userUpdate)
    {
        User user=getUser(userId);
        user.setAddress(userUpdate.getAddress());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setFullName(userUpdate.getFullName());
        user.setPhone(userUpdate.getPhone());
        return userRepository.save(user);
    }

}
