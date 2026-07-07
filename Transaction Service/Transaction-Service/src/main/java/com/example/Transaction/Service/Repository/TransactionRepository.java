package com.example.Transaction.Service.Repository;

import com.example.Transaction.Service.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(Long accountNumber);

}