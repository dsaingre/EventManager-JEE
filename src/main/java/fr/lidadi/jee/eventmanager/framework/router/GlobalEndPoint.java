package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.conf.Router;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by damien on 08/10/2016.
 */
public class GlobalEndPoint extends HttpServlet implements HttpErrorResponse {

    @Inject
    protected HttpControllerFactory httpControllerFactory;

    protected Map<Route, String> config;

    public GlobalEndPoint() {
        Router router = new Router();
        HttpConfig route = router.route(new HttpConfig());
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
        System.out.println(route);
        if(servlet != null){ // route found in config
            forwardToServlet(method, req, resp, servlet);
        } else { // 404
            notFound(resp);
        }

    }

    private void forwardToServlet(HttpMethod method, HttpServletRequest req, HttpServletResponse resp, String servlet) throws ServletException, IOException {

        Optional<HttpController> httpController = httpControllerFactory.create(servlet);

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
            System.out.println("Http controller is not found from name : " + servlet);
            notFound(resp);
        }
    }
}
