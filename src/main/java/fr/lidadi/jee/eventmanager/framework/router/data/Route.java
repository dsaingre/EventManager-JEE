package fr.lidadi.jee.eventmanager.framework.router.data;

/**
 * Created by damien on 08/10/2016.
 */
public class Route {

    private HttpMethod method;
    private String path;

    public Route(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
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
                '}';
    }
}
