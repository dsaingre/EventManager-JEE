package fr.lidadi.jee.eventmanager.framework.dao.sqldsl;

import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause.Like;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.clause.Where;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality.Equal;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.equality.SQLEqualityOperator;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.logical.And;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.logical.Or;
import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.logical.SQLLogicalOperator;

/**
 * Created by damien on 26/10/2016.
 */
public class SQLRequestFactory {


    // CLAUSES
    public static Where where(SQLLogicalOperator operator){
        return new Where(operator);
    }

    public static Like Like(Equal equal){
        return new Like(equal);
    }

    // EQUALITY
    public static Equal equal(String key, String value){
        return new Equal(key, value);
    }

    // LOGICAL
    public static And and(SQLOperator... operators){
        return new And(operators);
    }

    public static Or or(SQLOperator... operators){
        return new Or(operators);
    }


}
