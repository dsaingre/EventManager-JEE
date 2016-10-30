package fr.lidadi.jee.eventmanager.app.person;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.lidadi.jee.eventmanager.framework.Flashing;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.utils.Tuple;

/**
 * Created by damien on 17/10/2016.
 */
public class Persons implements HttpErrorResponse {

	private PersonService personService = new PersonService();
	private Flashing flashing = new Flashing();

	public void login(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("isLoginPage", "active");
		okJsp(servlet, req, resp, "/login.jsp");
	}

	public void logout(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().removeAttribute("authenticated");
		req.getSession().removeAttribute("user");
		redirect(req, resp, "/");
	}

	public void authentication(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Optional<Person> login = personService.login(email, password);
		if (login.isPresent()) {
			HttpSession session = req.getSession();
			session.setAttribute("user", login.get());
			String last_url = Optional.ofNullable(session.getAttribute("last_url")).orElse("/").toString();
			redirect(req, resp, last_url, new Tuple<String, String>("info", "Identification réussie !"));
			return;
		}

		redirect(req, resp, "/login", new Tuple<String, String>("error",
				"Impossible de vous identifier, merci de vérifier que les informations renseignées sont correctes."));
	}

	public void signup(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// flashing.flashing(req.getSession(), "error", "Wrong credentials");
		req.setAttribute("isSignupPage", "active");

		okJsp(servlet, req, resp, "/signup.jsp");
	}

	public void signupAction(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("NUMBER OF PARAMETERS :" + req.getParameterMap().size());

		// Creating a new profile
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String company = req.getParameter("company");

		Person newMember = new Person(UUID.randomUUID(), email, password, first_name, last_name, company, new Date(),
				new Date(), new LinkedList<>(), new LinkedList<>());

		PersonDao personDao = new PersonDao();

		Optional<Person> person = personDao.add(newMember);

		if (!person.isPresent()) {
			redirect(req, resp, "/signup",
					new Tuple<String, String>("error", "Un compte existe déjà pour cette adresse mail"));
		} else {
			redirect(req, resp, "/login", new Tuple<String, String>("info",
					"Votre compte a bien été créé ! Merci d'utiliser vos nouveaux identifiants pour vous connecter."));
		}
	}

	public void help(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		email = email == null ? "" : email;
		req.setAttribute("email", email);
		req.setAttribute("isLoginPage", "active");
		okJsp(servlet, req, resp, "/help.jsp");
	}

}
