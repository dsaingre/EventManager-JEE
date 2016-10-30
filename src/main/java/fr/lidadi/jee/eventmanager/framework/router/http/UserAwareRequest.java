package fr.lidadi.jee.eventmanager.framework.router.http;


import fr.lidadi.jee.eventmanager.app.person.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Optional;

/**
 * Created by damien on 24/10/2016.
 */
public class UserAwareRequest extends HttpServletRequestWrapper {

    private Optional<Person> person = Optional.empty();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public UserAwareRequest(HttpServletRequest request, Optional<Person> person) {
        super(request);
        this.person = person;
    }


    public Optional<Person> getUser() {
        return person;
    }
}
