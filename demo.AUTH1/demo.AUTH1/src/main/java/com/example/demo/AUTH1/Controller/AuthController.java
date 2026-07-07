package com.example.demo.AUTH1.Controller;


import com.example.demo.AUTH1.DTO.LoginRequest;
import com.example.demo.AUTH1.DTO.LoginResponse;
import com.example.demo.AUTH1.DTO.RegisterRequest;
import com.example.demo.AUTH1.DTO.RegisterResponse;
import com.example.demo.AUTH1.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    @GetMapping("/test")
    public String test() {
        return "Working";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        System.out.println("LOGIN API HIT");
        return service.login(request);

    }
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }
}