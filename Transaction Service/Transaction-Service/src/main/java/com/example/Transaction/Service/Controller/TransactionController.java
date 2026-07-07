package com.example.Transaction.Service.Controller;

import com.example.Transaction.Service.DTO.DepositRequest;
import com.example.Transaction.Service.DTO.TransactionHistoryResponse;
import com.example.Transaction.Service.DTO.TransactionResponse;
import com.example.Transaction.Service.DTO.WithdrawRequest;
import com.example.Transaction.Service.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // Test API
    @GetMapping("/test")
    public String test() {
        return "Transaction Service Working";
    }

    // Deposit
    @PostMapping("/deposit")
    public TransactionResponse deposit(@RequestBody DepositRequest request) {
        return service.deposit(request);
    }

    // Withdraw
    @PostMapping("/withdraw")
    public TransactionResponse withdraw(@RequestBody WithdrawRequest request) {
        return service.withdraw(request);
    }

    // Transaction History
    @GetMapping("/history/{accountNumber}")
    public List<TransactionHistoryResponse> getTransactionHistory(
            @PathVariable Long accountNumber) {

        return service.getTransactionHistory(accountNumber);
    }

}