package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.conf.Router;
import fr.lidadi.jee.eventmanager.framework.router.config.EmptyHttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by damien on 08/10/2016.
 */
public class GlobalEndPoint extends HttpServlet implements HttpErrorResponse {

//    @Inject
    protected HttpControllerFactory httpControllerFactory = new HttpControllerFactory();

    protected Map<Route, String> config;

    protected Map<String, HttpController> instanceMemoization = new HashMap<>();

    public GlobalEndPoint() {
        Router router = new Router();
        HttpConfig route = router.route(new EmptyHttpConfig());
        System.out.println(route);
        config = route.getConfig();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(HttpMethod.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(HttpMethod.POST, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(HttpMethod.PUT, req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(HttpMethod.DELETE, req, resp);
    }


    protected void processRequest(HttpMethod method, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Pattern p = Pattern.compile("^/[A-Za-z0-9]*");
        String path = req.getRequestURI().replaceFirst(p.pattern(), "");

        Route route = new Route(method, path);
        String servlet = config.get(route);

        List<AbstractMap.SimpleEntry<Route, Map<String, Object>>> matches = matches(method, path, config);

        if(matches.isEmpty()){
            System.out.println("Not route found");
            notFound(resp);
            return;
        }
        if(matches.size() > 1){
            System.out.println("Ambiguous route");
            System.out.println(matches);
            notFound(resp);
            return;
        }

        AbstractMap.SimpleEntry<Route, Map<String, Object>> routeThatMatch = matches.get(0);

//        routeThatMatch

        Map<String, Object> params = extractParamFromUrl(route, path);
        forwardToServlet(method, req, resp, servlet, params);

    }



    public List<AbstractMap.SimpleEntry<Route, Map<String, Object>>> matches(HttpMethod method, String path, Map<Route, String> config){
        return config.entrySet().stream()
                .map(Map.Entry::getKey)
                .map(route ->
                        new AbstractMap.SimpleEntry<>(route,
                                                      route.givenPathMatchesUrlPattern(path, method))
                )
                .filter(e -> e.getValue().isPresent())
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().get()))
                .collect(Collectors.toList());
    }


    private Map<String, Object> extractParamFromUrl(Route route, String path) {
        System.out.println("route : " + route);
        System.out.println("path : " + path);
        return new HashMap<>();
    }

    private void forwardToServlet(HttpMethod method, HttpServletRequest req, HttpServletResponse resp, String servletName, Map<String, Object> params) throws ServletException, IOException {

        Optional<HttpController> httpController = Optional.ofNullable(instanceMemoization.get(servletName));

        if (! httpController.isPresent()){
            httpController = httpControllerFactory.create(servletName);
            System.out.println("Memoization of " + servletName);
            instanceMemoization.put(servletName, httpController.get());
        }

        if (httpController.isPresent()){
            HttpController controller = httpController.get();
            if (method == HttpMethod.GET) {
                controller.get(req, resp);
            } else if (method == HttpMethod.POST) {
                controller.post(req, resp);
            } else if (method == HttpMethod.PUT) {
                controller.put(req, resp);
            } else if (method == HttpMethod.DELETE) {
                controller.delete(req, resp);
            } else {
                System.out.println("Http method " + method + " is not wired");
                notFound(resp);
            }
        }else{
            System.out.println("Http controller is not found from name : " + servletName);
            notFound(resp);
        }
    }
}
