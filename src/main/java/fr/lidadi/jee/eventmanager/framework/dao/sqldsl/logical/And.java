package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.logical;

import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by damien on 26/10/2016.
 */
public class And implements SQLLogicalOperator {

    private List<SQLOperator> operators;

    public And(SQLOperator... operators) {
        this.operators = Arrays.asList(operators);
    }

    @Override
    public <T> Predicate visit(CriteriaBuilder criteriaBuilder, Root<T> from) {
        Predicate[] expressions = GENERIC_ARRAY_FACTORY.create(Predicate.class, operators.size());
        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = operators.get(i).visit(criteriaBuilder, from);
        }

        return criteriaBuilder.and(expressions);
    }
}
