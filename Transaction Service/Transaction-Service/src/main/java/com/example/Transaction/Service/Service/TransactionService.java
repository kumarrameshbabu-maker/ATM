package com.example.Transaction.Service.Service;

import com.example.Transaction.Service.DTO.DepositRequest;
import com.example.Transaction.Service.DTO.TransactionHistoryResponse;
import com.example.Transaction.Service.DTO.TransactionResponse;
import com.example.Transaction.Service.DTO.WithdrawRequest;
import com.example.Transaction.Service.Entity.Transaction;
import com.example.Transaction.Service.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    // Deposit
    public TransactionResponse deposit(DepositRequest request) {

        Transaction transaction = new Transaction();

        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType("DEPOSIT");
        transaction.setTransactionDate(LocalDateTime.now());

        repository.save(transaction);

        return new TransactionResponse("Amount Deposited Successfully");
    }

    // Withdraw
    public TransactionResponse withdraw(WithdrawRequest request) {

        Transaction transaction = new Transaction();

        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType("WITHDRAW");
        transaction.setTransactionDate(LocalDateTime.now());

        repository.save(transaction);

        return new TransactionResponse("Amount Withdraw Successfully");
    }

    // Transaction History
    public List<TransactionHistoryResponse> getTransactionHistory(Long accountNumber) {

        List<Transaction> transactions = repository.findByAccountNumber(accountNumber);

        List<TransactionHistoryResponse> history = new ArrayList<>();

        for (Transaction transaction : transactions) {

            TransactionHistoryResponse response = new TransactionHistoryResponse();

            // Transaction ID
            response.setId(transaction.getTransactionId());

            // Account Number
            response.setAccountNumber(transaction.getAccountNumber());

            // Transaction Type
            response.setTransactionType(transaction.getTransactionType());

            // Amount
            response.setAmount(transaction.getAmount());

            // Transaction Date
            response.setTransactionDate(transaction.getTransactionDate());

            history.add(response);
        }

        return history;
    }
}