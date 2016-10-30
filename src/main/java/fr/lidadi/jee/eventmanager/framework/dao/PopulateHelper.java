package fr.lidadi.jee.eventmanager.framework.dao;

import static fr.lidadi.jee.eventmanager.framework.utils.ListBuilder.list;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import fr.lidadi.jee.eventmanager.app.event.Event;
import fr.lidadi.jee.eventmanager.app.event.EventDao;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.app.person.PersonDao;

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

		damien = new Person(UUID.randomUUID(), "dam.ray49@gmail.com", "damien", "Damien", "RAYMOND", "noeup'App",
				new Date(), new Date(), list(), list());

		lisa = new Person(UUID.randomUUID(), "zouill94@hotmail.fr", "aze", "lisa", "Panzani", "iRaiser", new Date(),
				new Date(), list(), list());

		persons.add(damien);
		persons.add(lisa);

		persons.forEach(personDao::add);
	}

	private void populateEvent() {
		EventDao eventDao = new EventDao();
		List<Event> events = list();

		// Event 1
		Event event = new Event(UUID.randomUUID(), "Mon anniv'", "grosse teuf chez wam", new Date(), new Date(),
				new Date(), "Chez wam", new Date(), new Date(), list(lisa, damien), "mon-anniv", list(), list());

		events.add(event);

		// Event 2
		Event event2 = new Event(UUID.randomUUID(), "Soirée JEE", "Dernier rush avant la fin du monde", new Date(),
				new Date(), null, "Aux mines", new Date(), new Date(), list(lisa, damien), "soiree-jee", list(),
				list());

		events.add(event2);

		// Event 3
		Event event3 = new Event(UUID.randomUUID(), "Hibernation", "Au revoir les ours", new Date(), new Date(),
				new Date(), "Pôle nord", new Date(), new Date(), list(lisa), "hibernation", list(), list());

		events.add(event3);

		events.forEach(eventDao::add);
	}

}
