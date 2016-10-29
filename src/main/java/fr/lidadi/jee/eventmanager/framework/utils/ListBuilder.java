package fr.lidadi.jee.eventmanager.framework.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by damien on 27/10/2016.
 */
public class ListBuilder {
    public static <T> List<T> list(T... ts){
        return new LinkedList<>(Arrays.asList(ts));
    }
}
