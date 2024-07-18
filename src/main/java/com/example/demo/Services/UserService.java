package com.example.demo.Services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserUpdate;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("User exists");
        }

        User user = new User (userDTO);
        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(passwordEncoder.encode(user.getPassword()));
        userDTO.setFullName(user.getFullName());
        userDTO.setPhone(user.getPhone());
        return userDTO;
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
    public UserDTO UserUpdate(Long userId ,UserUpdate userUpdate)
    {
        User user=getUser(userId);
        user.setAddress(userUpdate.getAddress());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        user.setFullName(userUpdate.getFullName());
        user.setPhone(userUpdate.getPhone());

        User updateuser= userRepository.save(user);
        return convertToDTO(updateuser);
    }

}
