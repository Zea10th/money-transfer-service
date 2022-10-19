package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Operation {
    @JsonProperty(value = "operationId")
    private String operationId;

    @JsonProperty(value = "code")
    private String code;

    public Operation operationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public int getOperationId() {
        return Integer.parseInt(operationId);
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return String.format("using operating ID %s with code %s",
                getOperationId(),
                getCode());
    }
}
