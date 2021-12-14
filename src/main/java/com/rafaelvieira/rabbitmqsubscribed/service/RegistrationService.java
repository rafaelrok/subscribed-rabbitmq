package com.rafaelvieira.rabbitmqsubscribed.service;

import com.rafaelvieira.rabbitmqsubscribed.config.RegistrationAMQPConfig;
import com.rafaelvieira.rabbitmqsubscribed.dto.RegistrationDTO;
import com.rafaelvieira.rabbitmqsubscribed.dto.StatusDTO;
import com.rafaelvieira.rabbitmqsubscribed.exceptions.RegistrationWithoutStatusException;
import com.rafaelvieira.rabbitmqsubscribed.exceptions.StatusNotFoundException;
import com.rafaelvieira.rabbitmqsubscribed.model.Registration;
import com.rafaelvieira.rabbitmqsubscribed.model.Status;
import com.rafaelvieira.rabbitmqsubscribed.repository.RegistrationRepository;
import com.rafaelvieira.rabbitmqsubscribed.repository.StatusRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    public static final String SEND_A_REGISTRATION_TO_QUEUE = "Send a subscription %s to queue.";
    public static final String STATUS_NOT_FOUND = "Status not found";
    public static final String CANNOT_SAVE_A_REGISTRATION_WITHOUT_STATUS = "Cannot save this register whithout status";
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);


    @Autowired
    private StatusRepository statusRepository;
    private ModelMapper mapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RegistrationRepository repository;

    public RegistrationService(RegistrationRepository repository, StatusRepository statusRepository,
                               ModelMapper mapper, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.mapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public RegistrationDTO saveRegistration(RegistrationDTO dto) {
        if (Optional.ofNullable(dto.getStatus()).isPresent()) {
            Registration registration = mapper.map(dto, Registration.class);
            registration.setStatus(loadStatus(dto.getStatus()));
            repository.saveAndFlush(registration);

            return dto;
        } else {
            LOGGER.error(CANNOT_SAVE_A_REGISTRATION_WITHOUT_STATUS);
            throw new RegistrationWithoutStatusException(CANNOT_SAVE_A_REGISTRATION_WITHOUT_STATUS);
        }
    }

    private Status loadStatus(StatusDTO status) {
        return statusRepository.findByName(status.getName())
                .orElseThrow(() -> new StatusNotFoundException(STATUS_NOT_FOUND));
    }

    public void sendRegistrationToRabbit(RegistrationDTO dto) {
        LOGGER.info(String.format(SEND_A_REGISTRATION_TO_QUEUE, dto.getId()));
        rabbitTemplate.convertAndSend(RegistrationAMQPConfig.EXCHANGE_NAME, "", dto);
    }

}
