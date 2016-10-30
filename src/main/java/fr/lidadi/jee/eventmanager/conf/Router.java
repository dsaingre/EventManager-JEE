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
                .get("/events/{slug}")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.fetch(STRING slug)")
                .get("/addevent")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.addView()")
                .post("/addEventAction")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.addEventAction()")
                .post("/events")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.add()")
                .get("/events/{id}/publish")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.publish(UUID id)")
                .get("/events/{id}/apply")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.apply(UUID id)")
                .get("/update/event/{id}")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.update(UUID id)")
                .post("/update/event/{id}")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.updateAction(UUID id)")
                .get("/events/{id}/delete")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.delete(UUID id)")
                .get("/myevents")
                    .to("fr.lidadi.jee.eventmanager.app.event.Events.myEvents()")

                .get("/events/{eventId}/participants/add")
                    .to("fr.lidadi.jee.eventmanager.app.participant.Participants.addView(UUID eventId)")
                .post("/events/{eventId}/participants/add")
                    .to("fr.lidadi.jee.eventmanager.app.participant.Participants.add(UUID eventId)");
    }
}
