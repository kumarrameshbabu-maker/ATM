package com.example.demo.Customer.Service.Feign;


import com.example.demo.Customer.Service.DTO.DepositRequest;
import com.example.demo.Customer.Service.DTO.WithdrawRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRANSACTION-SERVICE")
public interface TransactionFeignClient {

    @PostMapping("/transaction/deposit")
    String deposit(@RequestBody DepositRequest request);

    @PostMapping("/transaction/withdraw")
    String withdraw(@RequestBody WithdrawRequest request);

}