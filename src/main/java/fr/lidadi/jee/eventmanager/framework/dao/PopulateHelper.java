package fr.lidadi.jee.eventmanager.framework.dao;


import fr.lidadi.jee.eventmanager.app.event.Event;
import fr.lidadi.jee.eventmanager.app.event.EventDao;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.app.person.PersonDao;
import fr.lidadi.jee.eventmanager.app.slug.Slug;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static fr.lidadi.jee.eventmanager.framework.utils.ListBuilder.list;

/**
 * Created by damien on 25/10/2016.
 */
public class PopulateHelper {

    public void populate(EntityManager em) {
        populatePerson();
        populateEvent();
    }

    private Person damien;
    private Person lisa;

    private void populatePerson() {
        PersonDao personDao = new PersonDao();

        List<Person> persons = list();

        damien = new Person(UUID.randomUUID(),
                "dam.ray49@gmail.com",
                "damien",
                "Damien",
                "RAYMOND",
                "noeup'App",
                new Date(),
                new Date(),
                list(),
                list()
        );
        
        lisa = new Person(UUID.randomUUID(),
                "zouill94@hotmail.fr",
                "aze",
                "lisa",
                "Panzani",
                "iRaiser",
                new Date(),
                new Date(),
                list(),
                list()
        );

        persons.add(damien);
        persons.add(lisa);

        persons.forEach(personDao::add);
    }

    private void populateEvent() {
        EventDao eventDao = new EventDao();
        List<Event> events = list();

        // Event 1
        Event event = new Event(UUID.randomUUID(),
                "Mon anniv'",
                "grosse teuf chez wam",
                new Date(),
                new Date(),
                null,
                "Chez wam",
                new Date(),
                new Date(),
                list(lisa),
                list(),
                list(),
                list()
        );

        Slug slug = new Slug(UUID.randomUUID(), "mon-anniv", event);

        event.getSlugs().add(slug);

        events.add(event);

        // Event 2
        Event event2 = new Event(UUID.randomUUID(),
                "Soirée JEE",
                "Dernier rush avant la fin du monde",
                new Date(),
                new Date(),
                new Date(),
                "Aux mines",
                new Date(),
                new Date(),
                list(lisa),
                list(),
                list(),
                list()
        );

        Slug slug2 = new Slug(UUID.randomUUID(), "soiree-jee", event2);

        event2.getSlugs().add(slug2);

        events.add(event2);

        // Event 3
        Event event3 = new Event(UUID.randomUUID(),
                "Hibernation",
                "Au revoir les ours",
                new Date(),
                new Date(),
                new Date(),
                "Pôle nord",
                new Date(),
                new Date(),
                list(lisa),
                list(),
                list(),
                list()
        );

        Slug slug3 = new Slug(UUID.randomUUID(), "hibernation", event3);

        event3.getSlugs().add(slug3);

        events.add(event3);

        events.forEach(eventDao::add);
    }

}
