package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import java.util.Map;

/**
 * Created by damien on 08/10/2016.
 */
public class HttpConfigBindTo {

    private Map<Route, String> config;
    private Route key;

    HttpConfigBindTo(Map<Route, String> config, Route key) {
        this.config = config;
        this.key = key;
    }

    public HttpConfig to(String servletName){
        return new HttpConfig(config, key, servletName);
    }

    public <T extends HttpController> HttpConfig to(Class<T> servletClass){
        return new HttpConfig(config, key, servletClass.getName());
    }
}
