package fr.lidadi.jee.eventmanager.conf;

import fr.lidadi.jee.eventmanager.EventServlet;
import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;
import fr.lidadi.jee.eventmanager.framework.router.config.EmptyHttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.HttpRouter;

import static fr.lidadi.jee.eventmanager.framework.router.data.AllowedUrlType.*;

/**
 * Created by damien on 05/10/2016.
 */
public class Router implements HttpRouter {

    @Override
    public HttpConfig route(EmptyHttpConfig config) {
        return config
                .get("/events").to(EventServlet.class)
                .get("/events/{id}").to(EventServlet.class)
                    .withUrlParam("id", UUID)
                .get("/events/{eventId}/group/{groupId}/members").to(EventServlet.class)
                    .withUrlParam("eventId", UUID)
                    .withUrlParam("groupId", UUID)
                .post("/events").to(EventServlet.class)
                .put("/events").to(EventServlet.class)
                .delete("/events").to(EventServlet.class);
    }
}
