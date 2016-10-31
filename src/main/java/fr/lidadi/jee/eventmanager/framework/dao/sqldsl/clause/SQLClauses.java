package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by damien on 27/10/2016.
 */
public interface SQLClauses {

    <T> CriteriaQuery<T> visit(CriteriaQuery<T> tCriteriaQuery, CriteriaBuilder criteriaBuilder, Root<T> from);

}
