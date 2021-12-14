package com.rafaelvieira.rabbitmqsubscribed.model;

import com.rafaelvieira.rabbitmqsubscribed.config.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;

public class AuditRegistrationListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditRegistrationListener.class);

    @PrePersist
    public void onPrePersist(Registration registration) {
        audit(Type.INSERT, registration);
        LOGGER.info(String.format("Inserted registration %s", registration.getId()));
    }

    @PreUpdate
    public void onPreUpdate(Registration registration) {
        audit(Type.UPDATE, registration);
        LOGGER.info(String.format("Updated subscription %s", registration.getId()));
    }

    @Transactional(MANDATORY)
    public void audit(Type type, Registration registration) {
        EventHistory eventHistory = EventHistory.builder()
                .type(type)
                .subscriptionId(registration)
                .build();

        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(eventHistory);
    }
}
