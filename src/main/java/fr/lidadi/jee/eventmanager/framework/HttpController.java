package fr.lidadi.jee.eventmanager.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by damien on 08/10/2016.
 */
public abstract class HttpController implements HttpErrorResponse {

    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notFound(resp);
    }

    public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notFound(resp);
    }

    public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notFound(resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        notFound(resp);
    }

}
