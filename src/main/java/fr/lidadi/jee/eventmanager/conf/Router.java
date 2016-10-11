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
                .get("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetchAll()")
                .get("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetch(UUID id)");
//                    .withUrlParam("id", UUID)
//                .get("/events/{eventId}/group/{groupId}/members")
//                    .to("fr.lidadi.jee.eventmanager.EventServlet.test(UUID eventId, INT groupId)")
////                    .withUrlParam("eventId", UUID)
////                    .withUrlParam("groupId", UUID)
//                .post("/events")
//                    .to("fr.lidadi.jee.eventmanager.EventServlet.add()")
//                .put("/events")
//                    .to("fr.lidadi.jee.eventmanager.EventServlet.update()")
//                .delete("/events/{id}")
//                    .to("fr.lidadi.jee.eventmanager.EventServlet.delete(UUID id)");
    }
}
