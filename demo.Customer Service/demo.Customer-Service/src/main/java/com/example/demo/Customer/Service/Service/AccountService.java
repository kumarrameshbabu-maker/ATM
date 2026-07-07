package com.example.demo.Customer.Service.Service;

import com.example.demo.Customer.Service.DTO.AccountResponse;
import com.example.demo.Customer.Service.DTO.CreateAccountRequest;
import com.example.demo.Customer.Service.DTO.DepositRequest;
import com.example.demo.Customer.Service.DTO.WithdrawRequest;
import com.example.demo.Customer.Service.DTO.FundTransferRequest;
import com.example.demo.Customer.Service.DTO.FundTransferResponse;
import com.example.demo.Customer.Service.Entity.Account;
import com.example.demo.Customer.Service.Feign.TransactionFeignClient;
import com.example.demo.Customer.Service.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    // Create Account
    public AccountResponse createAccount(CreateAccountRequest request) {

        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setCustomerName(request.getCustomerName());
        account.setBalance(request.getBalance());

        repository.save(account);

        return new AccountResponse("Account Created Successfully");
    }

    // Deposit
    public AccountResponse deposit(DepositRequest request) {

        Account account = repository.findById(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        account.setBalance(account.getBalance() + request.getAmount());

        repository.save(account);

        transactionFeignClient.deposit(request);

        return new AccountResponse("Amount Deposited Successfully");
    }

    // Withdraw
    public AccountResponse withdraw(WithdrawRequest request) {

        Account account = repository.findById(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        if (account.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient Balance");
        }

        account.setBalance(account.getBalance() - request.getAmount());

        repository.save(account);

        transactionFeignClient.withdraw(request);

        return new AccountResponse("Amount Withdraw Successfully");
    }

    // Balance Inquiry
    public AccountResponse getBalance(Long accountNumber) {

        Account account = repository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        return new AccountResponse(
                "Current Balance: " + account.getBalance()
        );
    }

    // Fund Transfer
    public FundTransferResponse fundTransfer(FundTransferRequest request) {

        Account sender = repository.findById(request.getFromAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender Account Not Found"));

        Account receiver = repository.findById(request.getToAccountNumber())
                .orElseThrow(() -> new RuntimeException("Receiver Account Not Found"));

        if (sender.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient Balance");
        }

        // Debit sender
        sender.setBalance(sender.getBalance() - request.getAmount());

        // Credit receiver
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        repository.save(sender);
        repository.save(receiver);

        // Save sender transaction
        WithdrawRequest withdrawRequest = new WithdrawRequest();
       withdrawRequest.setAccountNumber(sender.getAccountNumber());
       withdrawRequest.setAmount(request.getAmount());

        transactionFeignClient.withdraw(withdrawRequest);

        // Save receiver transaction
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber(receiver.getAccountNumber());
        depositRequest.setAmount(request.getAmount());

        transactionFeignClient.deposit(depositRequest);

        return new FundTransferResponse("Fund Transfer Successful");
    }

}
