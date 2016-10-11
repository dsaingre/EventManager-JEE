package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;
import fr.lidadi.jee.eventmanager.framework.router.data.AllowedUrlType;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import java.util.*;

import static fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod.*;

/**
 * Created by damien on 08/10/2016.
 */
public class EmptyHttpConfig {

    protected List<Route> config = new LinkedList<>();

    public EmptyHttpConfig() {}

    public HttpConfigBindTo get(String url){
        return new HttpConfigBindTo(config, GET, url);
    }

    public HttpConfigBindTo post(String url){
        return new HttpConfigBindTo(config, POST, url);
    }

    public HttpConfigBindTo put(String url){
        return new HttpConfigBindTo(config, PUT, url);
    }

    public HttpConfigBindTo delete(String url){
        return new HttpConfigBindTo(config, DELETE, url);
    }

}
