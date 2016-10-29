package fr.lidadi.jee.eventmanager.framework.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import fr.lidadi.jee.eventmanager.app.event.EventDao;
import fr.lidadi.jee.eventmanager.app.event.Event;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.app.person.PersonDao;
import fr.lidadi.jee.eventmanager.app.slug.Slug;

/**
 * Created by damien on 25/10/2016.
 */
public class PopulateHelper {

	public void populate(EntityManager em) {

		PersonDao personDao = new PersonDao();

		List<Person> persons = new LinkedList<>();

		persons.add(new Person(UUID.randomUUID(), "dam.ray49@gmail.com", "damien", "Damien", "RAYMOND", "noeup'App",
				new Date(), new Date(), new LinkedList<Event>(), new LinkedList<Event>()));

		persons.forEach(personDao::add);

		populateEvent();
	}

	private void populateEvent() {
		EventDao eventDao = new EventDao();
		List<Event> events = new LinkedList<>();

		// Event 1
		Event event = new Event(UUID.randomUUID(), "Mon anniv'", "grosse teuf chez wam", new Date(),
				new Date(), new Date(), "Chez wam", new Date(), new Date(), new LinkedList<>(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>());

		Slug slug = new Slug(UUID.randomUUID(), "mon-anniv", event);

		event.getSlugs().add(slug);

		events.add(event);

		// Event 2
		Event event2 = new Event(UUID.randomUUID(), "Soirée JEE", "Dernier rush avant la fin du monde",
				new Date(), new Date(), new Date(), "Aux mines", new Date(), new Date(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

		Slug slug2 = new Slug(UUID.randomUUID(), "soiree-jee", event2);

		event2.getSlugs().add(slug2);

		events.add(event2);

		// Event 3
		Event event3 = new Event(UUID.randomUUID(), "Hibernation", "Au revoir les ours", new Date(),
				new Date(), new Date(), "Pôle nord", new Date(), new Date(), new LinkedList<>(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>());

		Slug slug3 = new Slug(UUID.randomUUID(), "hibernation", event3);

		event3.getSlugs().add(slug3);

		events.add(event3);

		events.forEach(eventDao::add);
	}

}
