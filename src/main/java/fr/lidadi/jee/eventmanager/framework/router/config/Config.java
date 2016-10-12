package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.router.config.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.config.Route;

import java.util.LinkedList;
import java.util.List;

import static fr.lidadi.jee.eventmanager.framework.router.config.HttpMethod.*;

/**
 * Created by damien on 12/10/2016.
 */
public class Config {

    public static class EmptyHttpConfig {

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

    public static class HttpConfig extends EmptyHttpConfig {

        private final Route lastRoute;

        HttpConfig(List<Route> config, HttpMethod method, String path, String classPath) {
            this.config = config;
            Route route = new Route(method, path, classPath);
            this.lastRoute = route;
            config.add(route);
        }

        public List<Route> getConfig() {
            return config;
        }

        @Override
        public String toString() {
            return "Routes : " + config;
        }
    }

    public static class HttpConfigBindTo {

        private List<Route> config;
        private final HttpMethod method;
        private final String path;

        HttpConfigBindTo(List<Route> config, HttpMethod method, String path) {
            this.config = config;
            this.method = method;
            this.path = path;
        }

        public HttpConfig to(String className){
            return new HttpConfig(config, method, path, className);
        }
    }
}
