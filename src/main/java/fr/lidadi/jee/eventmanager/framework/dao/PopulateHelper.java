package fr.lidadi.jee.eventmanager.framework.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import fr.lidadi.jee.eventmanager.app.event.EventDao;
import fr.lidadi.jee.eventmanager.app.event.EventEntity;
import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.app.person.PersonDao;
import fr.lidadi.jee.eventmanager.app.slug.SlugEntity;

/**
 * Created by damien on 25/10/2016.
 */
public class PopulateHelper {

	public void populate(EntityManager em) {

		PersonDao personDao = new PersonDao();

		List<Person> persons = new LinkedList<>();

		persons.add(new Person(UUID.randomUUID(), "dam.ray49@gmail.com", "damien", "Damien", "RAYMOND", "noeup'App",
				new Date(), new Date(), new LinkedList<EventEntity>(), new LinkedList<EventEntity>()));
		
		persons.add(new Person(UUID.randomUUID(), "zouill94@hotmail.fr", "aze", "Lisa", "Panzani", "iRaiser",
				new Date(), new Date(), new LinkedList<EventEntity>(), new LinkedList<EventEntity>()));

		persons.forEach(personDao::add);

		populateEventEntity();
	}

	private void populateEventEntity() {
		EventDao eventDao = new EventDao();
		List<EventEntity> events = new LinkedList<>();

		// Event 1
		EventEntity event = new EventEntity(UUID.randomUUID(), "Mon anniv'", "grosse teuf chez wam", new Date(),
				new Date(), new Date(), "Chez wam", new Date(), new Date(), new LinkedList<>(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>());

		SlugEntity slug = new SlugEntity(UUID.randomUUID(), "mon-anniv", event);

		event.getListOfSlug().add(slug);

		events.add(event);

		// Event 2
		EventEntity event2 = new EventEntity(UUID.randomUUID(), "Soirée JEE", "Dernier rush avant la fin du monde",
				new Date(), new Date(), new Date(), "Aux mines", new Date(), new Date(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>(), new LinkedList<>());

		SlugEntity slug2 = new SlugEntity(UUID.randomUUID(), "soiree-jee", event2);

		event2.getListOfSlug().add(slug2);

		events.add(event2);

		// Event 3
		EventEntity event3 = new EventEntity(UUID.randomUUID(), "Hibernation", "Au revoir les ours", new Date(),
				new Date(), new Date(), "Pôle nord", new Date(), new Date(), new LinkedList<>(), new LinkedList<>(),
				new LinkedList<>(), new LinkedList<>());

		SlugEntity slug3 = new SlugEntity(UUID.randomUUID(), "hibernation", event3);

		event3.getListOfSlug().add(slug3);

		events.add(event3);

		events.forEach(eventDao::add);
	}

}
