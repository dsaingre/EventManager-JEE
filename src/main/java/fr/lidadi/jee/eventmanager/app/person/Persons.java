package fr.lidadi.jee.eventmanager.app.person;

import fr.lidadi.jee.eventmanager.framework.Flashing;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.utils.Tuple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by damien on 17/10/2016.
 */
public class Persons implements HttpErrorResponse {

    private PersonService personService = new PersonService();
    private Flashing flashing = new Flashing();

    public void login(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isLoginPage", "active");
        okJsp(servlet, req, resp, "/login.jsp");
    }

    public void logout(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("authenticated");
        req.getSession().removeAttribute("user");
        redirect(req, resp, "/");
    }
    
	public void loginAction(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("NUMBER OF PARAMETERS :" + req.getParameterMap().size());
		System.out.println(req.getQueryString());
//		System.out.println(req.get);
		okJsp(servlet, req, resp, "/welcome.jsp");
	}

    public void authentication(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");


        Optional<Person> login = personService.login(email, password);
        if(login.isPresent()){
            HttpSession session = req.getSession();
            session.setAttribute("user", login.get());
            String last_url =
                    Optional.ofNullable(session.getAttribute("last_url"))
                            .orElse("/")
                            .toString();
            redirect(req, resp, last_url);
            return;
        }

        redirect(req, resp, "/login", new Tuple<String, String>("error", "Impossible de vous identifier, avez-vous inseré les bonnes informations ?"));
    }

    public void signup(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        flashing.flashing(req.getSession(), "error", "Wrong credentials");
        req.setAttribute("isSignupPage", "active");
        okJsp(servlet, req, resp, "/signup.jsp");
    }

	public void signupAction(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("NUMBER OF PARAMETERS :" + req.getParameterMap().size());
		System.out.println(req.getParameter("email"));
//		System.out.println(req.get);
		okJsp(servlet, req, resp, "/welcome.jsp");
    }

    public void help(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        email = email == null ? "" : email;
        req.setAttribute("email", email);
        req.setAttribute("isLoginPage", "active");
        okJsp(servlet, req, resp, "/help.jsp");
    }

}
