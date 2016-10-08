package fr.lidadi.jee.eventmanager.framework;

import javax.servlet.ServletException;
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

}
