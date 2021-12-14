package com.rafaelvieira.rabbitmqsubscribed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RegistrationWithoutStatusException extends RuntimeException {

    public RegistrationWithoutStatusException(String msg) {
        super(msg);
    }
}
