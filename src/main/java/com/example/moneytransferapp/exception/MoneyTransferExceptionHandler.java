package com.example.moneytransferapp.exception;

import com.example.moneytransferapp.service.MoneyTransferService;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MoneyTransferExceptionHandler {

    private final MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferExceptionHandler(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class})
    ResponseEntity<String> handleBadRequestException() {
        return moneyTransferService.getResponseError(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> handleRuntimeException() {
        return moneyTransferService.getResponseError(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
