package com.example.moneytransferapp.service;

import com.example.moneytransferapp.model.ResponseError;
import com.example.moneytransferapp.model.Transaction;
import com.example.moneytransferapp.repository.MoneyTransferRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.moneytransferapp.utility.SerializeUtility.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MoneyTransferExceptionServiceImplTest {

    @Test
    void getResponseError() {
        var badRequest = HttpStatus.BAD_REQUEST;

        var repositoryMock = Mockito.mock(MoneyTransferRepositoryImpl.class);

        var service = new MoneyTransferExceptionServiceImpl(repositoryMock);

        var expected = new ResponseEntity<>(
                serialize(new ResponseError().message(badRequest.getReasonPhrase()).id(0)),
                badRequest);


        var actual = service.getResponseError(badRequest);

        assertEquals(actual, expected);
    }
}