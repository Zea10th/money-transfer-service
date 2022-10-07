package com.example.moneytransferapp.controller;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.ResponseError;
import com.example.moneytransferapp.model.Transaction;
import com.example.moneytransferapp.service.MoneyTransferService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MoneyTransferController {

    public static AtomicInteger operationId = new AtomicInteger(0);

    private final MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<?> transfer(@RequestBody Transaction transaction) {
        int id = operationId.incrementAndGet();
        return moneyTransferService.transfer(id, transaction);
    }

    @PostMapping(value = "/confirmOperation")
    public ResponseEntity<?> confirmOperation(@RequestBody Operation operation) {
        int id = operationId.get();
        return moneyTransferService.confirmOperation(id, operation);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class})
    ResponseEntity<String> handleBadRequestException() {
        return new ResponseEntity<>(
                serialize(new ResponseError().id(MoneyTransferController.operationId.get())),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> handleRuntimeException() {
        return new ResponseEntity<>(
                serialize(new ResponseError().id(MoneyTransferController.operationId.get())),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private <T> String serialize(T obj) {
        try {
            return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
