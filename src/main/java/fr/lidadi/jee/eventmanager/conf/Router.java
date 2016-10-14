package fr.lidadi.jee.eventmanager.conf;

import fr.lidadi.jee.eventmanager.framework.router.config.Config;
import fr.lidadi.jee.eventmanager.framework.router.HttpRouter;

/**
 * Created by damien on 05/10/2016.
 */
public class Router implements HttpRouter {

    @Override
    public Config.HttpConfig route(Config.EmptyHttpConfig config) {
        return config
                .get("/")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.welcome()")
                .get("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetchAll()")
                .get("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetch(UUID id)")
                .get("/events/{eventId}/group/{groupId}/members")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.test(UUID eventId, INT groupId)")
                .post("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.add()")
                .put("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.update()")
                .delete("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.delete(UUID id)");
    }
}
