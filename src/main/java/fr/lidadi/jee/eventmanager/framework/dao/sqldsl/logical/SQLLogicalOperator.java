package fr.lidadi.jee.eventmanager.framework.dao.sqldsl.logical;

import fr.lidadi.jee.eventmanager.framework.dao.sqldsl.SQLOperator;
import fr.lidadi.jee.eventmanager.framework.utils.GenericArrayFactory;

/**
 * Created by damien on 26/10/2016.
 */
public interface SQLLogicalOperator extends SQLOperator {
    GenericArrayFactory GENERIC_ARRAY_FACTORY = new GenericArrayFactory();
}