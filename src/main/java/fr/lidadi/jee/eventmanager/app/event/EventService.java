package fr.lidadi.jee.eventmanager.app.event;

import java.util.*;

/**
 * Created by damien on 22/10/2016.
 */
public class EventService {

    private EventDao eventDao = new EventDao();

    public List<Event> fetchAll() {
        return eventDao.getAll();
    }

    public List<Event> fetchAllByOwner(UUID owner) {
        return eventDao.getByOwner(owner);
    }

    public Optional<Event> fetch(UUID id) {
        return eventDao.get(id);
    }
    public Optional<Event> fetchBySlug(String slug) {
        return eventDao.getBySlug(slug);
    }

}
