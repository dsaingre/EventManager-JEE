package fr.lidadi.jee.eventmanager.conf;

import fr.lidadi.jee.eventmanager.EventServlet;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.HttpRouter;

/**
 * Created by damien on 05/10/2016.
 */
public class Router implements HttpRouter {

    @Override
    public HttpConfig route(HttpConfig config) {
        return config
                .get("/events").to(EventServlet.class)
                .post("/events").to(EventServlet.class)
                .put("/events").to(EventServlet.class)
                .delete("/events").to(EventServlet.class);
    }
}
