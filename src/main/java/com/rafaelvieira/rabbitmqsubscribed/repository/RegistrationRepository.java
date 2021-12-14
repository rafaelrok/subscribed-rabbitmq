package com.rafaelvieira.rabbitmqsubscribed.repository;

import com.rafaelvieira.rabbitmqsubscribed.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, String> {
}
