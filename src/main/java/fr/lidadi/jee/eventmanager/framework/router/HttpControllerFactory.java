package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by damien on 08/10/2016.
 */
public class HttpControllerFactory {

    public Optional<HttpController> create(String className){
        try {
            Class<?> clazz = Class.forName(className);

            List<Constructor<?>> constructors = Arrays.asList(clazz.getConstructors());
            if (! constructors.isEmpty()){
                Constructor<?> constructor = constructors.get(0);
                return Optional.of((HttpController) constructor.newInstance());
            }else{
                return Optional.empty();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find class for name : " + className);
            return Optional.empty();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException : " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException : " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException : " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
