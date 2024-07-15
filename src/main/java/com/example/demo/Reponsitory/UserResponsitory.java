package com.example.demo.Reponsitory;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponsitory extends JpaRepository<User,Long> {
        User findByUsername(String username);
}
