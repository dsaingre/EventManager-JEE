package fr.lidadi.jee.eventmanager.app.person;

import fr.lidadi.jee.eventmanager.framework.dao.Dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by damien on 24/10/2016.
 */
public class PersonDao extends Dao<Person, UUID> {




    public Optional<Person> findByEmailAndPassword(String email, String password){

        // TODO hash pwd
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);

        Root<Person> from = query.from(Person.class);

        query
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(from.get("email"), email),
                        criteriaBuilder.equal(from.get("password"), password)
                ));


        TypedQuery<Person> query1 = em.createQuery(query);
        return query1.getResultList().stream().findFirst();
    }

}
