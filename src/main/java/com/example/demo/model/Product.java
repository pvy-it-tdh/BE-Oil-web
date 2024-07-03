package com.example.demo.model;

import jakarta.persistence.*;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private int price;
    private int stock;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
