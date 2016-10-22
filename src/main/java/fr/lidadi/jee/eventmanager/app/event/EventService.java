package fr.lidadi.jee.eventmanager.app.event;

import fr.lidadi.jee.eventmanager.dao.jpa.EventEntity;
import fr.lidadi.jee.eventmanager.dao.jpa.PersonnEntity;

import java.util.*;

/**
 * Created by damien on 22/10/2016.
 */
public class EventService {

    LinkedList<EventEntity> eventEntities = new LinkedList<>();

    public EventService() {
        eventEntities.add(new EventEntity(
                UUID.randomUUID(),
                "event name",
                new Date(),
                new Date(),
                new Date(),
                "Nantes",
                new Date(),
                new Date(),
                new LinkedList<PersonnEntity>(),
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
        ));
        eventEntities.add(new EventEntity(
                UUID.randomUUID(),
                "event name 2",
                new Date(),
                new Date(),
                new Date(),
                "Paris",
                new Date(),
                new Date(),
                new LinkedList<PersonnEntity>(),
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
        ));
    }

    public List<EventEntity> fetchAll() {
        return eventEntities;
    }


    public Optional<EventEntity> fetch(UUID id) {
        return eventEntities.stream()
                .filter(eventEntity -> eventEntity.getId().equals(id))
                .findFirst();
    }

}
