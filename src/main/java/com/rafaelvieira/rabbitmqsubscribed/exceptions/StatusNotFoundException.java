package com.rafaelvieira.rabbitmqsubscribed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(String msg) {
        super(msg);
    }
}
