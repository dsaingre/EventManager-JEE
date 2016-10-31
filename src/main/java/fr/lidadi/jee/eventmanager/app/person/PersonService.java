package fr.lidadi.jee.eventmanager.app.person;

import java.util.Optional;

/**
 * Created by damien on 24/10/2016.
 */
public class PersonService {

    private PersonDao personDao = new PersonDao();

    public Optional<Person> login(String email, String password) {
        return personDao.findByEmailAndPassword(email, password);
    }

}
