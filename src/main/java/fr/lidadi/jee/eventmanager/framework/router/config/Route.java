package fr.lidadi.jee.eventmanager.framework.router.config;

import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;

import java.util.*;
import java.util.stream.Collectors;

import static fr.lidadi.jee.eventmanager.framework.router.config.AllowedUrlType.*;

/**
 * Created by damien on 08/10/2016.
 */
public class Route {

    private PathAnalyser pathAnalyser = new PathAnalyser();

    private HttpMethod method;
    private String path;
    private Map<String, AllowedUrlType> params = new HashMap<>();
    private List<String> paramsNames = new LinkedList<>();


    private ClassPath classPath;

    public Route(HttpMethod method, String path, String classPath) {
        this.method = method;
        this.path = path;
        paramsNames = pathAnalyser.analysePath(path);
        try {
            this.classPath = pathAnalyser.analyseClassPath(classPath);
            this.params = this.classPath.getParams();
            if (!checkParamLists(this.classPath, paramsNames)) {
                System.out.println("Configuration error parameters are not coherent");
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private boolean checkParamLists(ClassPath classPath, List<String> paramsNames) {
        Set<String> classPathParams = classPath.getParams().keySet();
        Set<String> pathParams = getParamsOfUrl(paramsNames);
        return classPathParams.equals(pathParams);
    }

    private Set<String> getParamsOfUrl(List<String> paramsNames) {
        return paramsNames.stream()
            .filter(p -> p.startsWith("{"))
            .map(e -> e.substring(1, e.length() - 1))
            .collect(Collectors.toSet());
    }


    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, AllowedUrlType> getUrlParams() {
        return params;
    }

    public String getPath() {
        return path;
    }

    public void setUrlParams(String name, AllowedUrlType type) {
        params.put(name, type);
    }


    public ClassPath getClassPath() {
        return classPath;
    }

    public Optional<Map<String, Object>> givenPathMatchesUrlPattern(String path, HttpMethod method) {
        if (!method.equals(this.method)) {
            return Optional.empty();
        }
        if (this.getParamsOfUrl(paramsNames).isEmpty()) { // no params
            if (path.equals(this.path)) { // paths equals
                return Optional.of(new HashMap<>());
            }
            return Optional.empty();
        }
        return givenPathMatchesUrlPattern(path, paramsNames, new HashMap<>());
    }


    /**
     * This method is able to :
     * - determine if a given path matches the url pattern
     * - get param values
     * <p>
     * This algorithm goes through the path and the params.
     * For instance to parse the url `/users/598c6745-11d7-4c1a-a679-bd0b0c747a08/posts/123456789` :
     * - givenPathMatchesUrlPattern("/users/598c6745-11d7-4c1a-a679-bd0b0c747a08/posts/123456789",
     * List("/users/", "{userId}", "/posts/", "{postId}"),
     * Map())
     * <p>
     * - givenPathMatchesUrlPattern("598c6745-11d7-4c1a-a679-bd0b0c747a08/posts/123456789",
     * List("{userId}", "/posts/", "{postId}"),
     * Map())
     * <p>
     * - givenPathMatchesUrlPattern("/posts/123456789",
     * List("/posts/", "{postId}"),
     * Map("userId" -> 598c6745-11d7-4c1a-a679-bd0b0c747a08))
     * <p>
     * - givenPathMatchesUrlPattern("123456789",
     * List("{postId}"),
     * Map("userId" -> 598c6745-11d7-4c1a-a679-bd0b0c747a08))
     * <p>
     * - givenPathMatchesUrlPattern("",
     * List(),
     * Map("userId" -> 598c6745-11d7-4c1a-a679-bd0b0c747a08,
     * "postId" -> 123456789))
     *
     * @param path         to path to test (e.g. `/users/598c6745-11d7-4c1a-a679-bd0b0c747a08/posts/123456789`)
     * @param params       is the url pattern (e.g. List("/users/", "{userId}", "/posts/", "{postId}"))
     * @param parsedParams is the object to be returned
     * @return - Optional.empty : if the given path does not matches with the url pattern.
     * - Otherwise      : returns a map that links parameter name and their values
     * e.g. Map("userId" -> 598c6745-11d7-4c1a-a679-bd0b0c747a08, "postId" -> 123456789)
     */
    private Optional<Map<String, Object>> givenPathMatchesUrlPattern(String path, List<String> params, Map<String, Object> parsedParams) {
        if (arePathAndParamsSizeCoherent(path, params)) {
            return Optional.empty();
        }
        if (params.isEmpty()) {
            return Optional.of(parsedParams);
        }

        String headPartOfUrl = params.get(0);

        List<String> tailPartOfUrl = new LinkedList<>();
        if (params.size() > 1) {
            tailPartOfUrl = params.subList(1, params.size());
        }

        if (isParam(headPartOfUrl)) {
            return parseParam(path, parsedParams, headPartOfUrl, tailPartOfUrl);
        }
        int indexOfEndOfString = headPartOfUrl.length();

        if (indexOfEndOfString > path.length()) {
            return Optional.empty();
        }
        String nextPartsToParse = path.substring(indexOfEndOfString);
        if (! path.substring(0, indexOfEndOfString).endsWith("/") && ! nextPartsToParse.isEmpty()){
            return Optional.empty();
        }
        return givenPathMatchesUrlPattern(nextPartsToParse, tailPartOfUrl, parsedParams);

    }

    private boolean arePathAndParamsSizeCoherent(String path, List<String> params) {
        return (params.isEmpty() && !path.isEmpty()) || (!params.isEmpty() && path.isEmpty());
    }

    private Optional<Map<String, Object>> parseParam(String path, Map<String, Object> parsedParams, String headPartOfUrl, List<String> tailPartOfUrl) {

        int indexOfEndOfString = path.indexOf('/');
        if (indexOfEndOfString == -1) { // end of uri
            indexOfEndOfString = path.length();
        }

        String uriPartToParse = path.substring(0, indexOfEndOfString);
        String afterUriPartToParse = path.substring(indexOfEndOfString);

        String paramName = headPartOfUrl.substring(1, headPartOfUrl.length() - 1);
        AllowedUrlType allowedUrlType = this.params.get(paramName);


        if (allowedUrlType.equals(INT)) {
            try {
                int i = Integer.parseInt(uriPartToParse);
                parsedParams.put(paramName, i);
                return givenPathMatchesUrlPattern(afterUriPartToParse, tailPartOfUrl, parsedParams);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        if (allowedUrlType.equals(UUID)) {
            try {
                UUID i = java.util.UUID.fromString(uriPartToParse);
                parsedParams.put(paramName, i);
                return givenPathMatchesUrlPattern(afterUriPartToParse, tailPartOfUrl, parsedParams);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }

        }
        parsedParams.put(paramName, uriPartToParse); // default is String
        return givenPathMatchesUrlPattern(afterUriPartToParse, tailPartOfUrl, parsedParams);
    }

    private boolean isParam(String firstPartOfUrl) {
        return firstPartOfUrl.startsWith("{");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (getMethod() != route.getMethod()) return false;
        return getPath().equals(route.getPath());

    }

    @Override
    public int hashCode() {
        int result = getMethod().hashCode();
        result = 31 * result + getPath().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "method=" + method +
                ", path='" + path + '\'' +
                ", classPath=" + classPath +
                '}';
    }
}
