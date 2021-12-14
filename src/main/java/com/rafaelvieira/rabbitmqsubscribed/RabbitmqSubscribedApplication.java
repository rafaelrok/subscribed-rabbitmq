package com.rafaelvieira.rabbitmqsubscribed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EntityScan("com.rafaelvieira.rabbitmqsubscribed.model")
@EnableJpaRepositories("com.rafaelvieira.rabbitmqsubscribed.repository")
@ComponentScan("com.rafaelvieira.rabbitmqsubscribed")
@EnableWebSocket
@EnableWebSocketMessageBroker
@EnableJpaAuditing
public class RabbitmqSubscribedApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSubscribedApplication.class, args);
	}

}
