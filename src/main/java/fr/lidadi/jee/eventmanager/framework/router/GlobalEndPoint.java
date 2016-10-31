package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.app.person.Person;
import fr.lidadi.jee.eventmanager.conf.Router;
import fr.lidadi.jee.eventmanager.framework.HttpErrorResponse;
import fr.lidadi.jee.eventmanager.framework.router.config.ClassPath;
import fr.lidadi.jee.eventmanager.framework.router.config.Config;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpMethod;
import fr.lidadi.jee.eventmanager.framework.router.config.Route;
import fr.lidadi.jee.eventmanager.framework.router.http.SecuredRequest;
import fr.lidadi.jee.eventmanager.framework.router.http.UserAwareRequest;
import fr.lidadi.jee.eventmanager.framework.utils.Tuple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by damien on 08/10/2016.
 */
public class GlobalEndPoint extends HttpServlet implements HttpErrorResponse {

    protected List<Route> config;

    protected Map<String, Object> instanceMemoization = new HashMap<>();

    public GlobalEndPoint() {
        Router router = new Router();
        Config.HttpConfig route = router.route(new Config.EmptyHttpConfig());
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

        // Remove project name
        String contextPath = req.getContextPath();
        String path = req.getRequestURI().substring(contextPath.length());

        System.out.println("matches(" + method + " " + path + ") ?");

        List<AbstractMap.SimpleEntry<Route, Map<String, Object>>> matches = matches(method, path, config);

        if(matches.isEmpty()){
            System.out.println("Not route found");
            notFound(resp);
            return;
        }
        if(matches.size() > 1){
            System.out.println("Ambiguous route, more than 2 routes matches");
            System.out.println(matches);
            notFound(resp);
            return;
        }

        AbstractMap.SimpleEntry<Route, Map<String, Object>> routeThatMatch = matches.get(0);

        Route route = routeThatMatch.getKey();

        Map<String, Object> params = routeThatMatch.getValue();

        forwardToServlet(req, resp, route, params);

    }



    protected List<AbstractMap.SimpleEntry<Route, Map<String, Object>>> matches(HttpMethod method, String path, List<Route> config){
        return config.stream()
                .map(route ->
                        new AbstractMap.SimpleEntry<>(route,
                                                      route.givenPathMatchesUrlPattern(path, method))
                )
                .filter(e -> e.getValue().isPresent())
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().get()))
                .collect(Collectors.toList());
    }


    private void forwardToServlet(HttpServletRequest req, HttpServletResponse resp, Route route, Map<String, Object> params) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ClassPath classPath = route.getClassPath();
        String className = classPath.getClassName();
        String methodName = classPath.getMethodName();

        try{

            Class<?> clazz = Class.forName(className);

            Optional<Method> methodFound = findMethodByNameAndParams(params, methodName, clazz);

            Object instance = getInstanceFromClass(resp, className, clazz);


            if(! methodFound.isPresent()){
                notFound(resp);
                return;
            }

            Method method = methodFound.get();

            Object[] paramsToGiveToMethod = getParamsToGiveToMethod(req, resp, params);

            req.setAttribute("authenticated", false);

            Optional<Person> userOpt = getUserInSession(req);

            // When user has to be logged
            if (isSecureRequired(method)){
                if(! userOpt.isPresent()){
                    // Save url to redirect user to it last url
                    req.getSession().setAttribute("last_url", route.getPath());
                    redirect(req,
                            resp,
                            "/login",
                            new Tuple<String, String>("error", "Pour accéder à cette ressource, merci de vous connecter")
                    );
                    unauthorized(resp);
                    return;
                }
                req.setAttribute("authenticated", true);
                Person user = userOpt.get();
                req.setAttribute("user", user);
                paramsToGiveToMethod[1] = new SecuredRequest(req, user);
            }

            // When user can be logged
            if(isUserAwareRequired(method)){
                paramsToGiveToMethod[1] = new UserAwareRequest(req, userOpt);
            }


            method.setAccessible(true);
            method.invoke(instance, paramsToGiveToMethod);

            return;
        } catch (IllegalAccessException |
                InvocationTargetException |
                ClassNotFoundException |
                InstantiationException e) {
            e.printStackTrace();
        }

        notFound(resp);
    }

    private Optional<Person> getUserInSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return Optional.ofNullable((Person) session.getAttribute("user"));
    }

    private boolean isSecureRequired(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        Class<?> requestParameterType = parameterTypes[1];

        return requestParameterType !=  null &&
                SecuredRequest.class.isAssignableFrom(requestParameterType);
    }

    private boolean isUserAwareRequired(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        Class<?> requestParameterType = parameterTypes[1];

        return requestParameterType !=  null &&
                UserAwareRequest.class.isAssignableFrom(requestParameterType);
    }

    private Object getInstanceFromClass(HttpServletResponse resp, String className, Class<?> clazz) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Constructor<?>> constructors = Arrays.asList(clazz.getConstructors());
        if (constructors.isEmpty()){
            internalServerError(resp);
            return null;
        }

        Constructor<?> constructor = constructors.get(0);

        Optional<Object> instanceOpt = Optional.ofNullable(instanceMemoization.get(className));

        if (! instanceOpt.isPresent()){
            instanceOpt = Optional.of(constructor.newInstance());
            System.out.println("Memoization of " + className);
            instanceMemoization.put(className, instanceOpt.get());
        }
        return instanceOpt.get();
    }

    private Optional<Method> findMethodByNameAndParams(Map<String, Object> params, String methodName, Class<?> clazz) {
        return Stream.of(clazz.getMethods())
            .filter(m -> m.getName().equals(methodName) && (params.size() + 3) == m.getParameterCount())
            .findFirst();
    }

    private Object[] getParamsToGiveToMethod(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params){

        List<Object> argList = new LinkedList<>();
        argList.add(this);
        argList.add(req);
        argList.add(resp);

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            Object value = stringObjectEntry.getValue();
            argList.add(value);
        }

        return argList.toArray();
    }

}
