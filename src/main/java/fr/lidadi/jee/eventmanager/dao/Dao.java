package fr.lidadi.jee.eventmanager.dao;

import fr.lidadi.jee.eventmanager.framework.utils.GenericHelper;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Created by damien on 12/10/2016.
 */
public abstract class Dao<T extends Entity> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventmanager");

    protected EntityManager em = emf.createEntityManager();


    private GenericHelper genericHelper = new GenericHelper();

    private Class<T> getGenericName()
    {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<T> tClass = getGenericName();

    public List<T> getAll(){
        System.out.println(tClass.getSimpleName() + ".findAll");
        return em.createNamedQuery(tClass.getSimpleName() + ".findAll").getResultList();
    }


    public Optional<T> get(String code){
        return Optional.ofNullable(em.find(tClass, code));
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

    public Optional<T> delete(T eventEntity){
        T eventFound = em.find(tClass, eventEntity.getPrimaryKey());

        if(eventFound == null){
            return Optional.empty();
        }

        em.getTransaction().begin();

        em.remove(eventEntity);

        em.getTransaction().commit();

        return Optional.of(eventFound);
    }

}
