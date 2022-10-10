package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.*;
import com.example.moneytransferapp.repository.MoneyTransferRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    public static final Logger LOGGER = LogManager.getLogger(MoneyTransferServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final MoneyTransferRepositoryImpl repository;

    @Autowired
    public MoneyTransferServiceImpl(MoneyTransferRepositoryImpl repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ResponseEntity<?> transfer(Transaction transaction) {
        int id = repository.registerTransaction(transaction);
        LOGGER.info("Trying to provide transaction ID {} " +
                        "from card No {} valid till {} CVV {} to card No {} amount {} {}",
                id,
                transaction.getCardFromNumber(),
                transaction.getCardFromValidTill(),
                transaction.getCardFromCVV(),
                transaction.getCardToNumber(),
                transaction.getAmount().getValue(),
                transaction.getAmount().getCurrency());

        return new ResponseEntity<>(serialize(new ResponseOK().id(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmOperation(Operation operation) {
        int id = operation.getOperationId();

        if (id == repository.registerOperation(operation)) {
            String result = String.format("Transaction ID %d is successful.", id);
            repository.registerResult(result);
            LOGGER.info(result);
            return new ResponseEntity<>(serialize(new ResponseOK().id(id)), HttpStatus.OK);
        }

        repository.registerResult("Transaction ID " + id + " is failed cause: Session error.");
        return getResponseError(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getResponseError(HttpStatus status) {
        int id = repository.registerTransaction(null);
        String result = String.format("Transaction ID %d is failed cause: %d %s.",
                id, status.value(), status.getReasonPhrase());
        repository.registerOperation(null);
        repository.registerResult(result);
        LOGGER.error(result);
        return new ResponseEntity<>(
                serialize(new ResponseError().message(status.getReasonPhrase()).id(id)),
                status);
    }

    @Override
    public String getLogs() {
        return repository.getLogs();
    }

    private <T> String serialize(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
