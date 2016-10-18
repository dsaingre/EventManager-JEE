package fr.lidadi.jee.eventmanager.app.person;

import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by damien on 17/10/2016.
 */
public class Persons implements HttpErrorResponse {


    public void login(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isLoginPage", "active");
        okJsp(servlet, req, resp, "/login.jsp");
    }

    public void signup(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isSignupPage", "active");
        okJsp(servlet, req, resp, "/signup.jsp");
    }

    public void help(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        email = email == null ? "" : email;
        req.setAttribute("email", email);
        req.setAttribute("isLoginPage", "active");
        okJsp(servlet, req, resp, "/help.jsp");
    }

}
