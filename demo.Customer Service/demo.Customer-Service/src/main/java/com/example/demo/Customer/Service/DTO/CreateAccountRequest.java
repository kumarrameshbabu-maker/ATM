package com.example.demo.Customer.Service.DTO;


import lombok.Data;

@Data
public class CreateAccountRequest {

    private Long accountNumber;

    private String customerName;

    private Double balance;
}