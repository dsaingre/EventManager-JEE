package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;
import fr.lidadi.jee.eventmanager.framework.router.data.AllowedUrlType;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod.*;

/**
 * Created by damien on 08/10/2016.
 */
public class HttpConfig extends EmptyHttpConfig {

    private final Route lastRoute;

    HttpConfig(List<Route> config, HttpMethod method, String path, String classPath) {
        this.config = config;
        Route route = new Route(method, path, classPath);
        this.lastRoute = route;
        config.add(route);
    }


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

    public HttpConfig withUrlParam(String name, AllowedUrlType type){
        lastRoute.setUrlParams(name, type);
        return this;
    }

    public List<Route> getConfig() {
        return config;
    }

    @Override
    public String toString() {
        return "Routes : " + config;
    }
}
