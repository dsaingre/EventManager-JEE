package fr.lidadi.jee.eventmanager.app.event;

import static fr.lidadi.jee.eventmanager.framework.utils.Tuple.tuple;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.router.http.SecuredRequest;
import fr.lidadi.jee.eventmanager.framework.router.http.UserAwareRequest;
import fr.lidadi.jee.eventmanager.framework.utils.ExceptionConsumer;

/**
 * Created by damien on 08/10/2016.
 */
public class Events implements HttpErrorResponse {

	private EventService eventService = new EventService();

	public void welcome(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Event> events = this.eventService.fetchAllPublished();

		req.setAttribute("events", events);

		okJsp(servlet, req, resp, "/welcome.jsp");
	}

	public void search(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String location = req.getParameter("search");
		List<Event> events = null;

		if (location != null)
			events = this.eventService.fetchAllByLocation(location);

		req.setAttribute("events", events);

		okJsp(servlet, req, resp, "/welcome.jsp");
	}

	public void myEvents(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("isMyEvents", "active");
		req.setAttribute("events", this.eventService.fetchAllByOwner(req.getUser().getId()));
		okJsp(servlet, req, resp, "/event/myEvents.jsp");
	}

	public void fetch(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, String slug)
			throws ServletException, IOException {
		req.setAttribute("isMyEvents", "active");
		Optional<Event> event = this.eventService.fetchBySlug(slug);
		if (!event.isPresent()) {
			okJsp(servlet, req, resp, "/event/eventNotFound.jsp");
			return;
		}
		req.setAttribute("event", event.get());
		okJsp(servlet, req, resp, "/event/event.jsp");
	}

	public void publish(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp, UUID id)
			throws ServletException, IOException {
		actionIfUserIsAllowedAndEventFound(req, resp, id, event -> {
			eventService.publish(event);
			redirect(req, resp, "/myevents", tuple("info", "L'événement a bien été publié !"));
		});
	}

	public void delete(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp, UUID id)
			throws ServletException, IOException {
		actionIfUserIsAllowedAndEventFound(req, resp, id, event -> {
			eventService.delete(event);
			redirect(req, resp, "/myevents", tuple("info", "L'événement a bien été supprimé !"));
		});
	}

	private void actionIfUserIsAllowedAndEventFound(SecuredRequest req, HttpServletResponse resp, UUID id,
			ExceptionConsumer<Event> f) throws IOException {
		Optional<Event> eventOpt = eventService.fetch(id);

		if (eventOpt.isPresent()) {
			Event event = eventOpt.get();
			if (!eventService.isUserOwnerOfEvent(req.getUser(), event)) {
				redirect(req, resp, "/", tuple("error", "Page non trouvée"));
				return;
			}
			f.apply(event);
			return;
		}
		redirect(req, resp, "/myevents", tuple("error", "Impossible de trouver l'événement."));
	}

	public void apply(HttpServlet servlet, UserAwareRequest req, HttpServletResponse resp, UUID id)
			throws ServletException, IOException {

		Optional<Event> eventOpt = eventService.fetch(id);

		// Should never append but...
		if (!eventOpt.isPresent()) {
			redirect(req, resp, "/", tuple("error", "Événement non trouvé"));
			return;
		}

		Event event = eventOpt.get();

		Optional<Person> userOpt = req.getUser();
		// Logged user
		if (userOpt.isPresent()) {
			eventService.linkRegisteredPerson(event, userOpt.get());
			redirect(req, resp, "/events/" + event.getSlug());
			return;
		}

		redirect(req, resp, "/events/" + id + "/participants/add");

	}

	public void addView(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("isEventAdd", "active");
		okJsp(servlet, req, resp, "/event/addView.jsp");
	}

	public void addEventAction(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Person creator = req.getUser();
		List<Person> owners = new LinkedList<>();
		owners.add(creator);
		SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

		String name = req.getParameter("name");
		String adress = req.getParameter("address");
		String start_date = req.getParameter("start_date_submit");
		String start_time = req.getParameter("start_time");
		String end_date = req.getParameter("end_date_submit");
		String end_time = req.getParameter("end_time");
		String description = req.getParameter("description");

		boolean publication = "on".equals(req.getParameter("publication")) ? true : false;

		try {
			Event event = new Event(UUID.randomUUID(), name, description, sdf_date.parse(start_date + "-" + start_time),
					sdf_date.parse(end_date + "-" + end_time), null, adress, new Date(), new Date(), owners,
					Event.generateSlug(name, new Date()), new LinkedList<>(), new LinkedList<>());
			if (publication) {
				event.setPublishingDate(new Date());
			}

			EventDao eventDao = new EventDao();
			eventDao.add(event);
		} catch (ParseException e) {
			e.printStackTrace();
			redirect(req, resp, "/", tuple("error", "Mauvais format de dates"));
		}

		okJsp(servlet, req, resp, "/event/myEvents.jsp");
	}

}
