package fr.lidadi.jee.eventmanager.framework.router.data;

import java.util.Map;

/**
 * Created by damien on 11/10/2016.
 */
public class ClassPath {
    private String className;
    private String methodName;
    private Map<String, AllowedUrlType> params;

    public ClassPath(String className, String methodName, Map<String, AllowedUrlType> params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

    public Map<String, AllowedUrlType> getParams() {
        return params;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassPath classPath = (ClassPath) o;

        if (getClassName() != null ? !getClassName().equals(classPath.getClassName()) : classPath.getClassName() != null)
            return false;
        if (getMethodName() != null ? !getMethodName().equals(classPath.getMethodName()) : classPath.getMethodName() != null)
            return false;
        return getParams() != null ? getParams().equals(classPath.getParams()) : classPath.getParams() == null;

    }

    @Override
    public int hashCode() {
        int result = getClassName() != null ? getClassName().hashCode() : 0;
        result = 31 * result + (getMethodName() != null ? getMethodName().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        return result;
    }
}
