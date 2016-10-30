package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by damien on 30/10/2016.
 */
public class IsNotNull implements SQLEqualityOperator {

    private String field;

    public IsNotNull(String field) {
        this.field = field;
    }

    @Override
    public <T> Predicate visit(CriteriaBuilder criteriaBuilder, Root<T> from) {
        return criteriaBuilder.isNotNull(from.get(field));
    }
}
