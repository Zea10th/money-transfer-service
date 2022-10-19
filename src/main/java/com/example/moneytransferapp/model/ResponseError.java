package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ResponseError {
    @JsonProperty("message")
    private String message;

    @JsonProperty("id")
    private Integer id;

    public ResponseError message(String message) {
        this.message = message;
        return this;
    }

    public ResponseError id(Integer id) {
        this.id = id;
        return this;
    }
}
