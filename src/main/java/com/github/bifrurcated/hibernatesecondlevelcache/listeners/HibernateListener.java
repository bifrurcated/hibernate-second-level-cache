package com.github.bifrurcated.hibernatesecondlevelcache.listeners;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.stereotype.Service;

/**
 * Регистрация кастомных слушателей через {@link EntityManagerFactory}.
 */
@Service
@RequiredArgsConstructor
public class HibernateListener {

    private final EntityManagerFactory entityManagerFactory;
    private final ImmutableMergeEventListener immutableMergeEventListener;

    @PostConstruct
    private void init() {
        var sessionFactory = entityManagerFactory
            .unwrap(SessionFactoryImplementor.class);
        var registry = sessionFactory.getServiceRegistry()
            .getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.MERGE)
            .appendListener(immutableMergeEventListener);
    }
}
