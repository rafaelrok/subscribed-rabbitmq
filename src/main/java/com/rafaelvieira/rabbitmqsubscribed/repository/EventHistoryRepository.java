package com.rafaelvieira.rabbitmqsubscribed.repository;


import com.rafaelvieira.rabbitmqsubscribed.model.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends JpaRepository<EventHistory, Integer> {
}
