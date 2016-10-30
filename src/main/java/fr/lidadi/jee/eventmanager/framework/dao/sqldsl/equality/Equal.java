package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by damien on 26/10/2016.
 */
public class Equal implements SQLEqualityOperator {

    private String key;
    private Object value;

    public Equal(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public <T> Predicate visit(CriteriaBuilder criteriaBuilder, Root<T> from) {
        return criteriaBuilder.equal(from.get(key), value);
    }
}
