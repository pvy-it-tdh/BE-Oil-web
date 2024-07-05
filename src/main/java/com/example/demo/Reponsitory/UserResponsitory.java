package com.example.demo.Reponsitory;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponsitory extends JpaRepository<User,Long> {

}
