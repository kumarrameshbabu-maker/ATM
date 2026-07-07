package com.example.demo.AUTH1.Repository;

import com.example.demo.AUTH1.Entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByAccountNumber(Long accountNumber);

}