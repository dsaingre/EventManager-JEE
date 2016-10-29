package fr.lidadi.jee.eventmanager.app.event;

import fr.lidadi.jee.eventmanager.app.person.Person;

import java.util.*;

/**
 * Created by damien on 22/10/2016.
 */
public class EventService {

    LinkedList<EventEntity> eventEntities = new LinkedList<>();

    public EventService() {
        eventEntities.add(new EventEntity(
                UUID.randomUUID(),
                "Grosse fête du yaourt",
                "Yummy",
                new Date(),
                new Date(),
                new Date(),
                "Nantes",
                new Date(),
                new Date(),
                new LinkedList<Person>(),
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
        ));
        eventEntities.add(new EventEntity(
                UUID.randomUUID(),
                "Journée d'uniformisation des sets de table",
                "Parce que y'en a marre quand c'est pas bien rangé, mal aligné, pas assorti et surtout pas raccord",
                new Date(),
                new Date(),
                new Date(),
                "Paris",
                new Date(),
                new Date(),
                new LinkedList<Person>(),
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
        ));
    }

    public List<EventEntity> fetchAll() {
        return eventEntities;
    }

    public List<EventEntity> fetchAllByOwner(UUID owner) {
        return eventEntities;
    }


    public Optional<EventEntity> fetch(UUID id) {
        return eventEntities.stream()
                .filter(eventEntity -> eventEntity.getId().equals(id))
                .findFirst();
    }

}
