package fr.lidadi.jee.eventmanager.app.participant;

import fr.lidadi.jee.eventmanager.app.event.Event;
import fr.lidadi.jee.eventmanager.app.event.EventService;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.router.http.UserAwareRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.lidadi.jee.eventmanager.framework.utils.Tuple.tuple;

/**
 * Created by damien on 30/10/2016.
 */
public class Participants implements HttpErrorResponse {

    private EventService eventService = new EventService();
    private ParticipantService participantService = new ParticipantService();

    public void addView(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, UUID eventId)
            throws ServletException, IOException {
        Optional<Event> eventOpt = eventService.fetch(eventId);

        // This should never append but ...
        if (! eventOpt.isPresent()){
            redirect(req, resp, "/", tuple("error", "Évènement non trouvé"));
            return;
        }

        req.setAttribute("event", eventOpt.get());

        okJsp(servlet, req, resp, "/participant/add.jsp");
    }


    public void add(HttpServlet servlet, UserAwareRequest req, HttpServletResponse resp, UUID eventId)
            throws ServletException, IOException {

        Optional<Event> eventOpt = eventService.fetch(eventId);

        // This should never append but ...
        if (! eventOpt.isPresent()){
            redirect(req, resp, "/", tuple("error", "Évènement non trouvé"));
            return;
        }

        Event event = eventOpt.get();

        // If user is logger
        if(req.getUser().isPresent()){
            redirect(req, resp, "/events/" + event.getId() + "/apply");
            return;
        }

        // Let's add paticipant
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String company = req.getParameter("company");

        Participant participant = new Participant(email, firstName, lastName, company, event);

        participantService.add(participant);
        eventService.linkParticipant(event, participant);

        redirect(req, resp, "/events/" + event.getSlug(), tuple("info", "Votre inscription à bien été prise en compte"));
    }


}
