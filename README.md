# EventManager-JEE [![Build Status](https://travis-ci.com/Prygan/EventManager-JEE.svg?token=cocJpTsskx3dZagw8Jqi&branch=dev)](https://travis-ci.com/Prygan/EventManager-JEE)

Dino planner is a school project we did for the Ecole des Mines de Nantes. The aims are to use what we learned in JEE class but also to improve our ergonomic skills.


Table of contents :
- Architecture
- Router
- Flash
- CRUD database


## Architecture
```
src/
|--main/
   |--java/
      |--fr.lidadi.jee.eventmanager/
         |--app/ (contains business part (controllers, services, daos and entity) organised by entity)
         |--conf/
            |--Router.java (Router configuration)
         |--framework/
            |--dao/ (manage database connection, and interaction)
               |--sqldsl/ (A SQL DSL used by findBy())
               |--...
            |--jsptags (custom jsp tags)
            |--router  (router core code)
            |--utils   (some util tools)
   |--resources/META-INF/persistence.xml (database information)
   |--webapp/
      |--assets/
         |--css/ (contains all CSS)
         |--js/  (contains all JavaScript)
      |--WEB-INF/
         |--jsp/    (contains all JSP of the app organized by entity)
         |--app.tld (needed to use custom tags and funtions in JSP)
         |--web.xml (define routes (not used))
   
|--test

```


## Router
The usage of the router is very simple. The first step is to configure a route. And then, you just have to implement the method. The following example will guide you.

This is router is powerfull :
- it is able to **extract parameters from URL** and to **check there type** (UUID, STRING or INT). And then, give them to controller methods without require any cast !  
- it is able to filter according to controllers needs :
      - `HttpServletRequest` is default request (no filter)
      - `UserAwareRequest` user can be logged, but it is not required. Then it is possible to get an `Optional<Person>` thanks to `req.getUser()`.
      - `SecuredRequest` user have to be logged (error if not). Then it is to possible get the logged `Person` thanks to `req.getUser()`.
  
See examples below.

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

    public void fetchAll(HttpServlet servlet, SecuredRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ok(resp, "fetchAll");
    }

    public void welcome(HttpServlet servlet, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        okJsp(servlet, req, resp, "/welcome.jsp");
    }


    public void fetch(HttpServlet servlet, UserAwareRequest req, HttpServletResponse resp, UUID id) throws ServletException, IOException {
        ok(resp, "fetch(" + id + ")");
    }
}
```

## Flash
Flash messages are :
    - set in a controller 
    - display in view **only once** (the message is deleted after)
    
For the moment this is implemented using http sessions.    

### Set a message
Associate message "Wrong credentials" to key "error" : 
```java
private Flashing flashing = new Flashing();
// ...
flashing.flashing(req.getSession(), "error", "Wrong credentials");
```


### Test if a key is defined & display a message
Get the message associated to key "error" : 
```java
<c:if test="${app:flashExist(sessionScope, \"error\")}">
    <div class="alert alert-error">${app:consumeFlash(sessionScope, "error")}</div>
</c:if>
```

## CRUD database
In order to communicate with database, you have to :
- Create an entity that extends `fr.lidadi.jee.eventmanager.framework.dao.Entity`
- Create a Dao that extends `fr.lidadi.jee.eventmanager.framework.dao.Dao`

Then, you can use defined methods such as :
- getAll()
- get(PK id) : get an entity from it primary key (PK is the type of the primary key)
- findBy(SQLClauses sqlClauses) : equivalent to getAll with conditions 
- add(T entity) 
- update(T entity) 
- delete(PK id) 


### SQL DSL
We have defined a DSL to easily request entities with conditions. The two examples below show how it works.

#### Where
To get all event that is today, at Nantes or at Angers :
```java
findBy(
    where(
        and(
            equal("date", today),
            or(
                equal("location", "Nantes"),
                equal("location", "Angers"),
            )
        )
    )
)
```

#### Like (not implemented yet)
To get all event that location starts with "P". 
```java
findBy(
    like(
        equal("location", "P%")
    )
)
```

