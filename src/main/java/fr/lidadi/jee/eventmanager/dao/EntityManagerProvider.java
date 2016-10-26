package fr.lidadi.jee.eventmanager.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by damien on 26/10/2016.
 * EntityManagerProvider singleton
 */
public class EntityManagerProvider {

    private static EntityManagerProvider entityManagerProvider = null;

    public synchronized static EntityManagerProvider getInstance(){
        if (entityManagerProvider == null){
            entityManagerProvider = new EntityManagerProvider();
        }
        System.out.println("entityManagerProvider " + entityManagerProvider);
        return entityManagerProvider;
    }


    private final String ENTITY_MANAGER_NAME = "eventmanager";

    private EntityManager em = null;

    private CriteriaBuilder criteriaBuilder = null;

    private EntityManagerProvider() {
        System.out.println("EntityManagerProvider created");
    }

    private void init(){
        EntityManagerFactory eventmanager = Persistence.createEntityManagerFactory(ENTITY_MANAGER_NAME);
        em = eventmanager.createEntityManager();
        criteriaBuilder = em.getCriteriaBuilder();
        new PopulateHelper().populate(em);
    }

    public EntityManager getEntityManager() {
        if(em == null){
            init();
        }
        return em;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }
}
