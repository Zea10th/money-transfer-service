package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.Operation;
import com.example.moneytransferapp.model.ResponseError;
import com.example.moneytransferapp.model.Transaction;
import com.example.moneytransferapp.repository.MoneyTransferRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.moneytransferapp.utility.SerializeUtility.serialize;

@Service
public class MoneyTransferExceptionServiceImpl implements MoneyTransferExceptionService {

    public static final Logger LOGGER = LogManager.getLogger(MoneyTransferExceptionServiceImpl.class);
    private final MoneyTransferRepositoryImpl repository;

    @Autowired
    public MoneyTransferExceptionServiceImpl(MoneyTransferRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<String> getResponseError(HttpStatus status) {
        int id = repository.registerTransaction(new Transaction().getEmpty());
        String result = String.format("Transaction ID %d is failed cause: %d %s.",
                id, status.value(), status.getReasonPhrase());
        repository.registerOperation(new Operation().operationId("" + id));
        repository.registerResult(result);
        LOGGER.error(result);
        return new ResponseEntity<>(
                serialize(new ResponseError().message(status.getReasonPhrase()).id(id)),
                status);
    }
}
