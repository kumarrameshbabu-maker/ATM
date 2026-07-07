package com.example.demo.AUTH1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long accountNumber;

    private String password;
}