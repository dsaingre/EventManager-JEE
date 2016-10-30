package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by damien on 30/10/2016.
 */
public class IsNull implements SQLEqualityOperator {

    private String field;

    public IsNull(String field) {
        this.field = field;
    }

    @Override
    public <T> Predicate visit(CriteriaBuilder criteriaBuilder, Root<T> from) {
        return criteriaBuilder.isNull(from.get(field));
    }
}
