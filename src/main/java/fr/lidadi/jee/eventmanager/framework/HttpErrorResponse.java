package fr.lidadi.jee.eventmanager.framework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by damien on 08/10/2016.
 */
public interface HttpErrorResponse {

    default void notFound(HttpServletResponse resp) throws IOException {
        resp.setStatus(404);
        resp.getWriter().println("Page not found");
    }

    default void internalServerError(HttpServletResponse resp) throws IOException {
        resp.setStatus(500);
        resp.getWriter().println("Internal server error");
    }


    default void ok(HttpServletResponse resp, String message) throws IOException {
        resp.getWriter().println(message);
    }

    default void okJsp(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, String servletPath) throws IOException, ServletException {
        ServletContext servletContext = servlet.getServletContext();

        RequestDispatcher namedDispatcher = servletContext.getRequestDispatcher("/jsp" + servletPath);

        namedDispatcher.forward(req, resp);
    }
}
