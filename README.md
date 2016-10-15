# EventManager-JEE [![Build Status](https://travis-ci.com/Prygan/EventManager-JEE.svg?token=cocJpTsskx3dZagw8Jqi&branch=dev)](https://travis-ci.com/Prygan/EventManager-JEE)
A webapp to manage public events


## Router
The usage of the router is very simple. The first step is to configure a route. And then, you just have to implement the method. The following example will guide you.

### Route configuration
```java
public class Router implements HttpRouter {

    @Override
    public Config.HttpConfig route(Config.EmptyHttpConfig config) {
        return config
                .get("/")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.welcome()")
                .get("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetchAll()")
                .get("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.fetch(UUID id)")
                .get("/events/{eventId}/group/{groupId}/members")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.test(UUID eventId, INT groupId)")
                .post("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.add()")
                .put("/events")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.update()")
                .delete("/events/{id}")
                    .to("fr.lidadi.jee.eventmanager.EventServlet.delete(UUID id)");
    }
}
```

### Controller and method definition
```java
public class EventServlet implements HttpErrorResponse {

    public void fetchAll(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ok(resp, "fetchAll");
    }

    public void welcome(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        okJsp(servlet, req, resp, "/welcome.jsp");
    }


    public void fetch(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp, UUID id) throws ServletException, IOException {
        ok(resp, "fetch(" + id + ")");
    }
}
```
