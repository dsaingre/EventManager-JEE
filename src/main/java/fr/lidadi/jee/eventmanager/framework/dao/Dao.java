package fr.lidadi.jee.eventmanager.framework.dao;


import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause.SQLClauses;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.and;
import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.equal;
import static fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLRequestFactory.where;

/**
 * Created by damien on 12/10/2016.
 */
public abstract class Dao<T extends Entity, PK> {

    protected EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
    protected CriteriaBuilder criteriaBuilder = EntityManagerProvider.getInstance().getCriteriaBuilder();

    private Class<T> getGenericName()
    {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<T> tClass = getGenericName();

    public List<T> getAll(){
        CriteriaQuery<T> query = criteriaBuilder.createQuery(tClass);
        Root<T> from = query.from(tClass);

        query.select(from);
        return em.createQuery(query).getResultList();
    }


    public Optional<T> get(PK code){
        return Optional.ofNullable(em.find(tClass, code));
    }

    public List<T> findBy(SQLClauses sqlClauses){

        CriteriaQuery<T> query = criteriaBuilder.createQuery(tClass);

        Root<T> from = query.from(tClass);

        sqlClauses.visit(query, criteriaBuilder, from);

        return em.createQuery(query).getResultList();
    }

    public Optional<T> add(T eventEntity){
        em.getTransaction().begin();

        try{
            em.persist(eventEntity);
        }catch(EntityExistsException e) {
            return Optional.empty();
        }

        em.getTransaction().commit();
        return Optional.of(eventEntity);
    }


    public Optional<T> update(T eventEntity){
        em.getTransaction().begin();

        T eventEntityFound = em.find(tClass, eventEntity.getPrimaryKey());

        if(eventEntityFound == null){
            return Optional.empty();
        }

        eventEntityFound = eventEntity;

        em.persist(eventEntityFound);

        em.getTransaction().commit();
        return Optional.of(eventEntityFound);
    }

    public Optional<T> delete(PK pk){
        T eventFound = em.find(tClass, pk);

        if(eventFound == null){
            return Optional.empty();
        }

        em.getTransaction().begin();

        em.remove(eventFound);

        em.getTransaction().commit();

        return Optional.of(eventFound);
    }


}
