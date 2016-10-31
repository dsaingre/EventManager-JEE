package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause;

import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality.Equal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by damien on 27/10/2016.
 */
public class Like implements SQLClauses {

    private Equal equal;

    public Like(Equal equal) {
        this.equal = equal;
    }

    @Override
    public <T> CriteriaQuery<T> visit(CriteriaQuery<T> tCriteriaQuery, CriteriaBuilder criteriaBuilder, Root<T> from) {
        return null; // todo
    }
}
