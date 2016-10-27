package fr.lidadi.jee.eventmanager.framework.utils;

import java.lang.reflect.Array;

/**
 * Created by damien on 27/10/2016.
 */
public class GenericArrayFactory {
    /**
     * If T is an interface, it is not possible to build the array using : new Predicate[]()
     * You can use this to build array of T (even if T is an interface)
     * @param tClass
     * @param size
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T[] create(Class<T> tClass, int size){
        return (T[]) Array.newInstance(tClass, size);
    }
}
