package fr.lidadi.jee.eventmanager.app.person;

import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.and;
import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.equal;
import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.where;

import java.util.Optional;
import java.util.UUID;

import fr.lidadi.jee.eventmanager.framework.dao.Dao;

/**
 * Created by damien on 24/10/2016.
 */
public class PersonDao extends Dao<Person, UUID> {

	public Optional<Person> findByEmailAndPassword(String email, String password) {
		return this.findBy(where(and(equal("email", email), equal("password", password)))).stream().findFirst();
	}

	@Override
	public Optional<Person> add(Person p) {
		Optional<Person> personFound = findByEmail(p.getEmail());
		if (personFound.isPresent()) {
			return Optional.empty();
		}
		return super.add(p);
	}

	private Optional<Person> findByEmail(String email) {
		return this.findBy(where(and(equal("email", email)))).stream().findFirst();
	}

}
