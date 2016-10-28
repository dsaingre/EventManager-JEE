package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause;

import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLOperator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by damien on 27/10/2016.
 */
public class Where implements SQLClauses {

    private SQLOperator sqlOperator;

    public Where(SQLOperator sqlOperator) {
        this.sqlOperator = sqlOperator;
    }

    @Override
    public <T> CriteriaQuery<T> visit(CriteriaQuery<T> tCriteriaQuery, CriteriaBuilder criteriaBuilder, Root<T> from) {
        return tCriteriaQuery.where(
                sqlOperator.visit(criteriaBuilder, from)
        );
    }
}
