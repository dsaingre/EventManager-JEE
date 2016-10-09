package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod.*;
import static org.junit.Assert.*;

/**
 * Created by damien on 08/10/2016.
 */
public class RouteTest extends Mockito {

    GlobalEndPoint globalEndPoint;

    @Before
    public void setUp() throws Exception {
        globalEndPoint = new GlobalEndPoint();
    }

    @Test
    public void routeConfigurationTest() throws Exception {
        final String url = "/tests";
        final String className = "fr.lidadi.jee.eventmanager.framework.router.FakeHttpController";

        Map<Route, String> oracle = new HashMap<>();
        oracle.put(new Route(GET, url), className);
        oracle.put(new Route(POST, url), className);
        oracle.put(new Route(PUT, url), className);
        oracle.put(new Route(DELETE, url), className);


        HttpConfig route =
                ((HttpRouter) config ->
                        config
                            .get(url).to(FakeHttpController.class)
                            .post(url).to(FakeHttpController.class)
                            .put(url).to(FakeHttpController.class)
                            .delete(url).to(FakeHttpController.class)
                ).route(new HttpConfig());


        assertEquals(oracle, route.getConfig());

    }




    @Test
    public void routeConfiguredIsRouteCalledTest() throws Exception {
        // Route configuration
        Map<Route, String> config = new HashMap<>();
        String path = "/tests";
        config.put(new Route(GET, "/azdz"), "");
        config.put(new Route(GET, path), "");
        config.put(new Route(POST, "/azdmljazmldj"), "");
        globalEndPoint.config = config;

        // Mocks
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpControllerFactory httpControllerFactory = mock(HttpControllerFactory.class);
        HttpController httpController = mock(HttpController.class);
        PrintWriter printWriter = new PrintWriter(new StringWriter());

        // Mocks method answers
        when(req.getRequestURI()).thenReturn("/project" + path);
        when(httpControllerFactory.create(any(String.class))).thenReturn(Optional.of(httpController));
        when(resp.getWriter()).thenReturn(printWriter);

        globalEndPoint.httpControllerFactory = httpControllerFactory;

        // Test method
        globalEndPoint.processRequest(GET, req, resp);

        // Checks
        verify(httpControllerFactory, times(1)).create(any(String.class));
        verify(httpController, atLeast(1)).get(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}