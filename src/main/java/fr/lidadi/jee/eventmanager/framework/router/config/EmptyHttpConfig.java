package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;
import fr.lidadi.jee.eventmanager.framework.router.data.AllowedUrlType;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod.*;

/**
 * Created by damien on 08/10/2016.
 */
public class EmptyHttpConfig {

    protected Map<Route, String> config = new HashMap<>();

    public EmptyHttpConfig() {}

    public HttpConfigBindTo get(String url){
        return new HttpConfigBindTo(config, new Route(GET, url));
    }

    public HttpConfigBindTo post(String url){
        return new HttpConfigBindTo(config, new Route(POST, url));
    }

    public HttpConfigBindTo put(String url){
        return new HttpConfigBindTo(config, new Route(PUT, url));
    }

    public HttpConfigBindTo delete(String url){
        return new HttpConfigBindTo(config, new Route(DELETE, url));
    }

}
