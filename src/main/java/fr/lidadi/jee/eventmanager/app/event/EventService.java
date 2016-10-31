package fr.lidadi.jee.eventmanager.app.event;

import fr.lidadi.jee.eventmanager.app.participant.Participant;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.framework.Flashing;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by damien on 22/10/2016.
 */
public class EventService {

    private EventDao eventDao = new EventDao();

    public List<Event> fetchAllPublished() {
        return eventDao.getAllPublished();
    }
    
    public List<Event> fetchAllByLocation(String location) {
    	return eventDao.getByLocation(location);
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

    public boolean isUserOwnerOfEvent(Person person, Event event){
        return event.getOwners().contains(person);
    }

    public void publish(Event event) {
        // update publish field (value = now)
        event.setPublishingDate(new Date());
        eventDao.update(event);
    }

    public void delete(Event event) {
        eventDao.delete(event.getId());
    }

    public void linkRegisteredPerson(Event event, Person person) {
        event.addRegisteredPerson(person);
        eventDao.update(event);
    }

    public void linkParticipant(Event event, Participant participant) {
        event.addParticipant(participant);
        eventDao.update(event);

    }
}
