package fr.lidadi.jee.eventmanager.framework.dao;

import fr.lidadi.jee.eventmanager.app.event.EventEntity;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.app.person.PersonDao;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by damien on 25/10/2016.
 */
public class PopulateHelper {

    public void populate(EntityManager em){

        PersonDao personDao = new PersonDao();

        List<Person> persons = new LinkedList<>();

        persons.add(new Person(
                UUID.randomUUID(),
                "dam.ray49@gmail.com",
                "damien",
                "Damien",
                "RAYMOND",
                "noeup'App",
                new Date(),
                new Date(),
                new LinkedList<EventEntity>(),
                new LinkedList<EventEntity>()
        ));

        persons.forEach(personDao::add);

    }

}
