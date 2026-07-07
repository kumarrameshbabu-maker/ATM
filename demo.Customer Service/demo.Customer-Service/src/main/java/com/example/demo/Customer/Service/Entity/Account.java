package com.example.demo.Customer.Service.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    private Long accountNumber;

    private String customerName;

    private Double balance;
}