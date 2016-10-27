package fr.lidadi.jee.eventmanager.app.event;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.router.http.SecuredRequest;

/**
 * Created by damien on 08/10/2016.
 */
public class Events implements HttpErrorResponse {

    private EventService eventService = new EventService();





    public void welcome(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EventDao eventDao = new EventDao();

        System.out.println(eventDao.getAll());

        okJsp(servlet, req, resp, "/welcome.jsp");
    }






    public void fetchAll(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("events", this.eventService.fetchAll());

        okJsp(servlet, req, resp, "/event/events.jsp");
    }

    public void fetch(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, UUID id) throws ServletException, IOException {
        Optional<EventEntity> event = this.eventService.fetch(id);
        if(! event.isPresent()){
            okJsp(servlet, req, resp, "/event/eventNotFound.jsp");
            return;
        }
        req.setAttribute("event", event.get());
        okJsp(servlet, req, resp, "/event/event.jsp");
    }

    public void addView(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        okJsp(servlet, req, resp, "/event/addView.jsp");
    }



    public void myEvents(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events", this.eventService.fetchAllByOwner(req.getUser().getId()));
        okJsp(servlet, req, resp, "/event/myEvents.jsp");
    }


}
