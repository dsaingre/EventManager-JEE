package fr.lidadi.jee.eventmanager.framework.router.http;


import fr.lidadi.jee.eventmanager.app.person.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by damien on 24/10/2016.
 */
public class SecuredRequest extends HttpServletRequestWrapper {

    private Person person;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public SecuredRequest(HttpServletRequest request, Person person) {
        super(request);
        this.person = person;
    }


    public Person getUser() {
        return person;
    }
}
