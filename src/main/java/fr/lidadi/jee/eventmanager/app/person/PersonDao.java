package fr.lidadi.jee.eventmanager.app.person;

import fr.lidadi.jee.eventmanager.framework.dao.Dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Optional;
import java.util.UUID;

import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.*;

/**
 * Created by damien on 24/10/2016.
 */
public class PersonDao extends Dao<Person, UUID> {

    public Optional<Person> findByEmailAndPassword(String email, String password){
        return this.findBy(
                where(and(
                    equal("email", email),
                    equal("password", password))
                 )
        ).stream().findFirst();
    }

}
