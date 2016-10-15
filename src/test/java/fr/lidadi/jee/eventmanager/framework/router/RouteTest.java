package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.router.config.AllowedUrlType;
import fr.lidadi.jee.eventmanager.framework.router.config.Config;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.config.Route;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static fr.lidadi.jee.eventmanager.framework.router.config.HttpMethod.*;
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

        List<Route> oracle = new LinkedList<>();
        oracle.add(new Route(GET,    "/tests",      "com.lidadi.Test.findAll()"));
        oracle.add(new Route(POST,   "/tests",      "com.lidadi.Test.add()"));
        oracle.add(new Route(PUT,    "/tests/{id}", "com.lidadi.Test.update(UUID id)"));
        oracle.add(new Route(DELETE, "/tests/{id}", "com.lidadi.Test.update(UUID id)"));


        Config.HttpConfig route =
                ((HttpRouter) config ->
                        config
                            .get("/tests")
                                .to("com.lidadi.Test.findAll()")
                            .post("/tests")
                                .to("com.lidadi.Test.add()")
                            .put("/tests/{id}")
                                .to("com.lidadi.Test.update(UUID id)")
                            .delete("/tests/{id}")
                                .to("com.lidadi.Test.update(UUID id)")
                ).route(new Config.EmptyHttpConfig());


        assertEquals(oracle, route.getConfig());

    }


    @Test
    public void givenPathMatches_zeroParam() throws Exception {
        Route route = new Route(HttpMethod.GET, "/tests", "com.ladadi.Test.findAll()");
        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertTrue(stringObjectMap.isPresent());
        assertEquals(oracle, stringObjectMap.get());
    }

    @Test
    public void givenPathMatches_oneCorrectParam1() throws Exception {
        Route route = new Route(HttpMethod.GET, "/tests/{id}", "com.ladadi.Test.findAll(UUID id)");
        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();
        oracle.put("id", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertTrue(stringObjectMap.isPresent());
        assertEquals(oracle, stringObjectMap.get());
    }

    @Test
    public void givenPathMatches_oneCorrectParam2() throws Exception {
        Route route = new Route(HttpMethod.GET, "/tests/{id}", "com.ladadi.Test.findAll(INT id)");
        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/123456789", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();
        oracle.put("id", 123456789);

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertTrue(stringObjectMap.isPresent());
        assertEquals(oracle, stringObjectMap.get());
    }

    @Test
    public void givenPathMatches_oneCorrectParam3() throws Exception {
        Route route = new Route(HttpMethod.GET, "/hello/{name}", "com.ladadi.Test.findAll(STRING name)");
        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/hello/Damien", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();
        oracle.put("name", "Damien");

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertTrue(stringObjectMap.isPresent());
        assertEquals(oracle, stringObjectMap.get());
    }

    @Test
    public void givenPathMatches_oneNotCorrectParam() throws Exception {
        Route route = new Route(HttpMethod.GET, "/tests/{id}", "com.ladadi.Test.findAll(INT id)");
        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();
        oracle.put("id", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertFalse(stringObjectMap.isPresent());
    }


    @Test
    public void givenPathMatches_twoCorrectParam() throws Exception {
        Route route = new Route(HttpMethod.GET, "/tests/{testId}/users/{userId}", "com.ladadi.Test.findAll(UUID testId, INT userId)");

        Optional<Map<String, Object>> stringObjectMap = route.givenPathMatchesUrlPattern("/tests/598c6745-11d7-4c1a-a679-bd0b0c747a08/users/123456789", HttpMethod.GET);

        Map<String, Object> oracle = new HashMap<>();
        oracle.put("testId", UUID.fromString("598c6745-11d7-4c1a-a679-bd0b0c747a08"));
        oracle.put("userId", 123456789);

        System.out.println("stringObjectMap : " + stringObjectMap);
        assertTrue(stringObjectMap.isPresent());
        assertEquals(oracle, stringObjectMap.get());
    }
}