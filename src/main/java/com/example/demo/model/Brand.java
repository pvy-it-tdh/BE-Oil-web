package com.example.demo.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Brand {
    private Long brandId;
    private String name;
    private String description;
    private Date creatAt;

}
