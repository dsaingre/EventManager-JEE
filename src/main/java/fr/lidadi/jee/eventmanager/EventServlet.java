package fr.lidadi.jee.eventmanager;

import fr.lidadi.jee.eventmanager.framework.HttpController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by damien on 08/10/2016.
 */
public class EventServlet extends HttpController {

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello " + req.getParameter("name"));
    }


    public void fetchAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("fetchAll");
    }


    public void fetch(HttpServletRequest req, HttpServletResponse resp, UUID id) throws ServletException, IOException {
        resp.getWriter().println("fetch(" + id + ")");
    }
}
