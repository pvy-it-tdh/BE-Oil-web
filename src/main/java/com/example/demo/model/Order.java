package com.example.demo.model;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Double totalAmount;
    private String status;
    private Date createAt;
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;


}
