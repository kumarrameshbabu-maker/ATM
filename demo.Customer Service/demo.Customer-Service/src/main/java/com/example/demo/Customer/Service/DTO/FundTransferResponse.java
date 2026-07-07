package com.example.demo.Customer.Service.DTO;

public class FundTransferResponse {

    private String message;

    public FundTransferResponse() {
    }

    public FundTransferResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}