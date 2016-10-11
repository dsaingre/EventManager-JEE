package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by damien on 08/10/2016.
 */
public class HttpConfigBindTo {

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

//    public <T extends HttpController> HttpConfig to(Class<T> servletClass){
//        return new HttpConfig(config, key, servletClass.getName());
//    }
}
