package fr.lidadi.jee.eventmanager.framework.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by damien on 30/10/2016.
 */
@FunctionalInterface
public interface ExceptionConsumer<T> {
    void apply(T t) throws IOException;
}
