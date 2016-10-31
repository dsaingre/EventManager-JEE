package fr.lidadi.jee.eventmanager.framework;

import fr.lidadi.jee.eventmanager.framework.utils.Tuple;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

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

    default void unauthorized(HttpServletResponse resp) throws IOException {
        resp.setStatus(403);
        resp.getWriter().println("Unauthorized");
    }


    default void ok(HttpServletResponse resp, String message) throws IOException {
        resp.getWriter().println(message);
    }

    default void redirect(HttpServletRequest req, HttpServletResponse resp, String target, Tuple<String, String>... flashs) throws IOException {
        System.out.println("Redirecting to " + target);
        Flashing flashing = new Flashing();
        Arrays.asList(flashs)
                .forEach(flash -> {
                            flashing.flashing(req.getSession(), flash.getFst(), flash.getScn());
                        }
                    );
        resp.sendRedirect(req.getContextPath() + target);
    }

    default void okJsp(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, String servletPath) throws IOException, ServletException {
        ServletContext servletContext = servlet.getServletContext();

        RequestDispatcher namedDispatcher = servletContext.getRequestDispatcher("/WEB-INF/jsp" + servletPath);

        namedDispatcher.forward(req, resp);
    }
}
