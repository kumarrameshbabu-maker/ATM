package com.example.demo.Customer.Service.Controller;

import com.example.demo.Customer.Service.DTO.*;
import com.example.demo.Customer.Service.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/create")
    public AccountResponse create(@RequestBody CreateAccountRequest request) {
        return service.createAccount(request);
    }

    @PostMapping("/deposit")
    public AccountResponse deposit(@RequestBody DepositRequest request) {
        return service.deposit(request);
    }

    @PostMapping("/withdraw")
    public AccountResponse withdraw(@RequestBody WithdrawRequest request) {
        return service.withdraw(request);
    }

    @GetMapping("/balance/{accountNumber}")
    public AccountResponse getBalance(@PathVariable Long accountNumber) {
        return service.getBalance(accountNumber);
    }

    @GetMapping("/test")
    public String test() {
        return "Account Service Working";
    }
    @PostMapping("/transfer")
    public FundTransferResponse transfer(@RequestBody FundTransferRequest request) {
        return service.fundTransfer(request);
    }
}