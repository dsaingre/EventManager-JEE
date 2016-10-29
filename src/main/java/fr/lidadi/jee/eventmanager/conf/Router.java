package fr.lidadi.jee.eventmanager.conf;

import fr.lidadi.jee.eventmanager.framework.router.HttpRouter;
import fr.lidadi.jee.eventmanager.framework.router.config.Config;

/**
 * Created by damien on 05/10/2016.
 */
public class Router implements HttpRouter {

    @Override
    public Config.HttpConfig route(Config.EmptyHttpConfig config) {
        return config
                .get("/login")
                    .to("fr.lidadi.jee.eventmanager.app.person.Persons.login()")
                .get("/logout")
                    .to("fr.lidadi.jee.eventmanager.app.person.Persons.logout()")
                .post("/authentication")
                    .to("fr.lidadi.jee.eventmanager.app.person.Persons.authentication()")
                .get("/signup")
                    .to("fr.lidadi.jee.eventmanager.app.person.Persons.signup()")
                .post("/signupAction")
                	.to("fr.lidadi.jee.eventmanager.app.person.Persons.signupAction()")
                .get("/help")
                    .to("fr.lidadi.jee.eventmanager.app.person.Persons.help()")

                .get("/")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.welcome()")
                .get("/events")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.fetchAll()")
                .get("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.fetch(UUID id)")
                .get("/addevent")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.addView()")
                .post("/addEventAction")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.addEventAction()")
                .post("/events")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.add()")
                .put("/events")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.update()")
                .delete("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.delete(UUID id)")
                .get("/myevents")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.myEvents()");
    }
}
