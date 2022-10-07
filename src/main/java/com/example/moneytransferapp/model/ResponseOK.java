package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ResponseOK {
    @JsonProperty(value = "operationId")
    private String operationId;

    public ResponseOK id(Integer id) {
        this.operationId = String.valueOf(id.intValue());
        return this;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
