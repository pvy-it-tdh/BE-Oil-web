package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name ="user_id")
}
