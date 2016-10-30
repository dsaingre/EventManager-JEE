package fr.lidadi.jee.eventmanager.app.event;

import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.framework.dao.Dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.*;

/**
 * Created by damien on 18/10/2016.
 */
public class EventDao extends Dao<Event, UUID> {


    public List<Event> getByOwner(UUID owner) {
        return this.getAll().stream()
                .filter(event ->
                        event.getOwners().stream()
                            .map(Person::getId)
                            .filter(owner::equals)
                            .findFirst()
                            .isPresent()
                ).collect(Collectors.toList());
    }

    public Optional<Event> getBySlug(String slug) {
        return this.findBy(where(
                equal("slug", slug)
        )).stream().findFirst();
    }
}
