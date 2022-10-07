package com.example.moneytransferapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ResponseError {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("id")
    private Integer id = null;

    public ResponseError message(String message) {
        this.message = message;
        return this;
    }

    public ResponseError id(Integer id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
