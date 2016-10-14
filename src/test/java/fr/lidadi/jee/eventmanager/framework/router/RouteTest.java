//package fr.lidadi.jee.eventmanager.framework.router;
//
//import fr.lidadi.jee.eventmanager.framework.HttpController;
//import fr.lidadi.jee.eventmanager.framework.router.config.EmptyHttpConfig;
//import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
//import fr.lidadi.jee.eventmanager.framework.router.data.AllowedUrlType;
//import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
//import fr.lidadi.jee.eventmanager.framework.router.data.Route;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//import static fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod.*;
//import static org.junit.Assert.*;

/**
 * Created by damien on 08/10/2016.
 */
//public class RouteTest extends Mockito {
//
//
//    GlobalEndPoint globalEndPoint;
//
//    @Before
//    public void setUp() throws Exception {
//        globalEndPoint = new GlobalEndPoint();
//    }
//
//    //@Test
//    public void routeConfigurationTest() throws Exception {
//        final String url = "/tests";
//        final String className = "fr.lidadi.jee.eventmanager.framework.router.FakeHttpController";
//
//        Map<Route, String> oracle = new HashMap<>();
//        oracle.put(new Route(GET, url), className);
//        oracle.put(new Route(POST, url), className);
//        oracle.put(new Route(PUT, url), className);
//        oracle.put(new Route(DELETE, url), className);
//
//
//        HttpConfig route =
//                ((HttpRouter) config ->
//                        config
//                            .get(url).to(FakeHttpController.class)
//                            .post(url).to(FakeHttpController.class)
//                            .put(url).to(FakeHttpController.class)
//                            .delete(url).to(FakeHttpController.class)
//                ).route(new EmptyHttpConfig());
//
//
//        assertEquals(oracle, route.getConfig());
//
//    }




    //@Test
//    public void routeConfiguredIsRouteCalledTest() throws Exception {
//        // Route configuration
//        Map<Route, String> config = new HashMap<>();
//        String path = "/tests";
//        config.put(new Route(GET, "/azdz"), "");
//        config.put(new Route(GET, path), "");
//        config.put(new Route(POST, "/azdmljazmldj"), "");
//        globalEndPoint.config = config;
//
//        // Mocks
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        HttpControllerFactory httpControllerFactory = mock(HttpControllerFactory.class);
//        HttpController httpController = mock(HttpController.class);
//        PrintWriter printWriter = new PrintWriter(new StringWriter());
//
//        // Mocks method answers
//        when(req.getRequestURI()).thenReturn("/project" + path);
//        when(httpControllerFactory.create(any(String.class))).thenReturn(Optional.of(httpController));
//        when(resp.getWriter()).thenReturn(printWriter);
//
//        globalEndPoint.httpControllerFactory = httpControllerFactory;
//
//        // Test method
//        globalEndPoint.processRequest(GET, req, resp);
//
//        // Checks
//        verify(httpControllerFactory, times(1)).create(any(String.class));
//        verify(httpController, atLeast(1)).get(any(HttpServletRequest.class), any(HttpServletResponse.class));
//    }


    //@Test
//    public void memoizationTest() throws Exception {
//        // Route configuration
//        Map<Route, String> config = new HashMap<>();
//        String path = "/tests";
//        config.put(new Route(GET, path), "");
//        globalEndPoint.config = config;
//
//        // Mocks
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        HttpControllerFactory httpControllerFactory = mock(HttpControllerFactory.class);
//        HttpController httpController = mock(HttpController.class);
//        PrintWriter printWriter = new PrintWriter(new StringWriter());
//
//        // Mocks method answers
//        when(req.getRequestURI()).thenReturn("/project" + path);
//        when(httpControllerFactory.create(any(String.class))).thenReturn(Optional.of(httpController));
//        when(resp.getWriter()).thenReturn(printWriter);
//
//        globalEndPoint.httpControllerFactory = httpControllerFactory;
//
//        // Test method
//        globalEndPoint.processRequest(GET, req, resp);
//        globalEndPoint.processRequest(GET, req, resp);
//        globalEndPoint.processRequest(GET, req, resp);
//        globalEndPoint.processRequest(GET, req, resp);
//        globalEndPoint.processRequest(GET, req, resp);
//
//        // Checks
//        verify(httpControllerFactory, times(1)).create(any(String.class));
//    }




//
//    @Test
//    public void givenPathMatches_zeroParam() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/tests");
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertTrue(stringObjectMap.isPresent());
//        assertEquals(oracle, stringObjectMap.get());
//    }
//
//    @Test
//    public void givenPathMatches_oneCorrectParam1() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/tests/{id}");
//        route.setUrlParams("id", AllowedUrlType.UUID);
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//        oracle.put("id", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertTrue(stringObjectMap.isPresent());
//        assertEquals(oracle, stringObjectMap.get());
//    }
//
//    @Test
//    public void givenPathMatches_oneCorrectParam2() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/tests/{id}");
//        route.setUrlParams("id", AllowedUrlType.INT);
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/123456789", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//        oracle.put("id", 123456789);
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertTrue(stringObjectMap.isPresent());
//        assertEquals(oracle, stringObjectMap.get());
//    }
//
//    @Test
//    public void givenPathMatches_oneCorrectParam3() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/hello/{name}");
//        route.setUrlParams("name", AllowedUrlType.STRING);
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/hello/Damien", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//        oracle.put("name", "Damien");
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertTrue(stringObjectMap.isPresent());
//        assertEquals(oracle, stringObjectMap.get());
//    }
//
//    @Test
//    public void givenPathMatches_oneNotCorrectParam() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/tests/{id}");
//        route.setUrlParams("id", AllowedUrlType.INT);
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//        oracle.put("id", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertFalse(stringObjectMap.isPresent());
//    }
//
//
//    @Test
//    public void givenPathMatches_twoCorrectParam() throws Exception {
//        Route route = new Route(HttpMethod.GET, "/tests/{testId}/users/{userId}");
//        route.setUrlParams("testId", AllowedUrlType.UUID);
//        route.setUrlParams("userId", AllowedUrlType.INT);
//
//        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08/users/123456789", HttpMethod.GET);
//
//        Map<String, Object> oracle = new HashMap<>();
//        oracle.put("testId", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));
//        oracle.put("userId", 123456789);
//
//        System.out.println("stringObjectMap : " + stringObjectMap);
//        assertTrue(stringObjectMap.isPresent());
//        assertEquals(oracle, stringObjectMap.get());
//    }
//}