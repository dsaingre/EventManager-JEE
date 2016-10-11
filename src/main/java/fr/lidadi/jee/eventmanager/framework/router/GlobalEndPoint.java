package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.HttpController;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.conf.Router;
import fr.lidadi.jee.eventmanager.framework.router.config.EmptyHttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.data.ClassPath;
import fr.lidadi.jee.eventmanager.framework.router.data.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.data.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by damien on 08/10/2016.
 */
public class GlobalEndPoint extends HttpServlet implements HttpErrorResponse {

//    @Inject
    protected HttpControllerFactory httpControllerFactory = new HttpControllerFactory();

    protected List<Route> config;

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

//        Route route = new Route(method, path);
//        String servlet = config.get(route);

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

        ClassPath classPath = routeThatMatch.getKey().getClassPath();

        Map<String, Object> params = routeThatMatch.getValue();

        forwardToServlet(method, req, resp, classPath, params);

    }



    public List<AbstractMap.SimpleEntry<Route, Map<String, Object>>> matches(HttpMethod method, String path, List<Route> config){
        return config.stream()
                .map(route ->
                        new AbstractMap.SimpleEntry<>(route,
                                                      route.givenPathMatchesUrlPattern(path, method))
                )
                .filter(e -> e.getValue().isPresent())
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().get()))
                .collect(Collectors.toList());
    }


    private void forwardToServlet(HttpMethod method, HttpServletRequest req, HttpServletResponse resp, ClassPath classPath, Map<String, Object> params) throws ServletException, IOException {

        String className = classPath.getClassName();
        String methodName = classPath.getMethodName();

        try{

            Class<?> clazz = Class.forName(className);

            List<Constructor<?>> constructors = Arrays.asList(clazz.getConstructors());
            if (constructors.isEmpty()){
                internalServerError(resp);
                return;
            }
            Constructor<?> constructor = constructors.get(0);
            Object instance = constructor.newInstance();

            List<Class<?>> classList = new LinkedList<>();
            classList.add(HttpServletRequest.class);
            classList.add(HttpServletResponse.class);

            List<Object> argList = new LinkedList<>();
            argList.add(req);
            argList.add(resp);

            for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
                Object value = stringObjectEntry.getValue();

                classList.add(value.getClass());
                argList.add(value);
            }


            Class<?>[] argsClasses = classList.toArray(new Class<?>[classList.size()]);
            Object[] args = argList.toArray();


            Method classMethod = clazz.getDeclaredMethod(methodName, argsClasses);
            classMethod.setAccessible(true);
            classMethod.invoke(instance, args);
            return;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }


//        Optional<HttpController> httpController = Optional.ofNullable(instanceMemoization.get(classPath.getClassName()));
//
//        if (! httpController.isPresent()){
//            httpController = httpControllerFactory.create(servletName);
//            System.out.println("Memoization of " + servletName);
//            instanceMemoization.put(servletName, httpController.get());
//        }

        notFound(resp);
    }

}
