package com.example.moneytransferapp.exception;

import com.example.moneytransferapp.service.MoneyTransferExceptionService;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class MoneyTransferExceptionHandler {

    private final MoneyTransferExceptionService moneyTransferExceptionService;

    @Autowired
    public MoneyTransferExceptionHandler(MoneyTransferExceptionService moneyTransferExceptionService) {
        this.moneyTransferExceptionService = moneyTransferExceptionService;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,
            JsonParseException.class,
            ResponseStatusException.class})
    ResponseEntity<String> handleBadRequestException() {
        return moneyTransferExceptionService.getResponseError(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> handleRuntimeException() {
        return moneyTransferExceptionService.getResponseError(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
