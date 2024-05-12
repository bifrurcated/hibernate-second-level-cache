package com.github.bifrurcated.hibernatesecondlevelcache.listeners;

import org.hibernate.HibernateException;
import org.hibernate.annotations.Immutable;
import org.hibernate.event.spi.MergeContext;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.MergeEventListener;
import org.springframework.stereotype.Component;

/**
 * Перехватываем событие merge, чтобы выбросить исключение, если сущность помечена
 * аннотацией {@link Immutable}.
 */
@Component
public class ImmutableMergeEventListener implements MergeEventListener {
    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        onMerge(event, null);
    }

    @Override
    public void onMerge(MergeEvent event, MergeContext copiedAlready) throws HibernateException {
        if (event == null) {
            return;
        }
        Class<?> clazz = event.getOriginal().getClass();
        if (clazz.isAnnotationPresent(Immutable.class)) {
            throw new HibernateException("Attempts to update an immutable entity: "
                + clazz.getName());
        }
    }
}
