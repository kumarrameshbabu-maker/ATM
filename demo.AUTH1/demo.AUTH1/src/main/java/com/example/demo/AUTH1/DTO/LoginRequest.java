package com.example.demo.AUTH1.DTO;


import lombok.Data;

@Data
public class LoginRequest {

    private Long accountNumber;

    private String password;

}
