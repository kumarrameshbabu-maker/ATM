package com.example.demo.AUTH1.Service;

import com.example.demo.AUTH1.DTO.LoginRequest;
import com.example.demo.AUTH1.DTO.LoginResponse;
import com.example.demo.AUTH1.DTO.RegisterRequest;
import com.example.demo.AUTH1.DTO.RegisterResponse;
import com.example.demo.AUTH1.Entity.AuthUser;
import com.example.demo.AUTH1.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register
    public RegisterResponse register(RegisterRequest request) {

        if (repository.findByAccountNumber(request.getAccountNumber()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }

        AuthUser user = new AuthUser();
        user.setAccountNumber(request.getAccountNumber());

        // Store encrypted password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        return new RegisterResponse("User Registered Successfully");
    }

    // Login
    public LoginResponse login(LoginRequest request) {

        AuthUser user = repository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Invalid Account Number"));

        // Compare encrypted password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return new LoginResponse(
                "Login Successful",
                "JWT_TOKEN"
        );
    }
}