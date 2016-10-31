package fr.lidadi.jee.eventmanager.framework.dao.sqldsl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by damien on 26/10/2016.
 */
public interface SQLOperator {
    <T> Predicate visit(CriteriaBuilder criteriaBuilder, Root<T> from);
}
