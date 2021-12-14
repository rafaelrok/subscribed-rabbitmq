package com.rafaelvieira.rabbitmqsubscribed.controller;

import com.rafaelvieira.rabbitmqsubscribed.dto.RegistrationDTO;
import com.rafaelvieira.rabbitmqsubscribed.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @ApiOperation(value = "Save a registration on system")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration saved"),
            @ApiResponse(code = 500, message = "An exception occurred"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody RegistrationDTO registration) {
        service.sendRegistrationToRabbit(registration);
    }
}

