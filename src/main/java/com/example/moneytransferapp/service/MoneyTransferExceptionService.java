package com.example.moneytransferapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface MoneyTransferExceptionService {

    ResponseEntity<String> getResponseError(HttpStatus status);

}
