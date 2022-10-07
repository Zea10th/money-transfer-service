package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferServiceImpl.class);
    private final ObjectMapper objectMapper;

    public MoneyTransferServiceImpl() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ResponseEntity<?> transfer(int id, Transaction transaction) {
        ResponseEntity<?> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    serialize(new ResponseOK().id(id)),
                    HttpStatus.OK
            );
            LOGGER.info("Transaction ID " + id + " has been completed.");

        } catch (Exception e) {
            throw new RuntimeException(e);
            //LOGGER.error("Transaction ID " + id + " has been failed cause \"500 Internal server error\".");
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<?> confirmOperation(int id, Operation operation) {
        ResponseEntity<?> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(
                    serialize(new ResponseOK().id(id)),
                    HttpStatus.OK
            );
            LOGGER.info("Transaction ID " + id + " has been confirmed.");

        } catch (Exception e) {
            //LOGGER.error("Transaction ID " + id + " hasn't been confirmed cause \"500 Internal server error\".");
            throw new RuntimeException(e);
        }

        return responseEntity;
    }

    private <T> String serialize(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
