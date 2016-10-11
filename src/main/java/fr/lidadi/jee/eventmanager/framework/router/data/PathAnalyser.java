package fr.lidadi.jee.eventmanager.framework.router.data;

import fr.lidadi.jee.eventmanager.framework.router.ConfigurationException;

import java.util.*;

/**
 * Created by damien on 11/10/2016.
 */
public class PathAnalyser {

    /**
     * Split url at parameter position
     *      e.g. : analysePath("/users/{userId}/posts/{postId}")
     *          returns List("/users/", "{userId}", "/posts/", "{postId}")
     * @param path the url
     * @return url splited
     */
    public List<String> analysePath(String path){
        if(path.isEmpty()){
            return new LinkedList<>();
        }
        List<String> res = new LinkedList<>();
        int indexOfOpeningBrace = path.indexOf('{');
        if(indexOfOpeningBrace != -1){
            String start = path.substring(0, indexOfOpeningBrace);
            String end = path.substring(indexOfOpeningBrace);
            int indexOfClosingBrace = end.indexOf('}');
            if(indexOfClosingBrace != -1){
                indexOfClosingBrace += 1;
                String param = end.substring(0, indexOfClosingBrace);
                String afterParam = end.substring(indexOfClosingBrace);
                res.add(start);
                res.add(param);
                res.addAll(analysePath(afterParam));
                return res;
            }else{
                System.out.println("missing `}` in route config");
            }

        }
        res.add(path);
        return res;
    }


    public ClassPath analyseClassPath(String classPath) throws ConfigurationException {
        System.out.println(classPath);
        int lastIndexOfPoint = classPath.lastIndexOf('.');
        if(lastIndexOfPoint == -1){
            throw new ConfigurationException("Malformed class name, cannot find '.' char");
        }

        String className = classPath.substring(0, lastIndexOfPoint);
        String method = classPath.substring(lastIndexOfPoint + 1); // remove '.'

        int indexOfFirstParenthesis = method.indexOf('(');
        if(indexOfFirstParenthesis == -1){
            throw new ConfigurationException("Malformed class name, should contain '('");
        }
        if(! method.endsWith(")")){
            throw new ConfigurationException("Malformed class name, should end with ')'");
        }
        String methodName = method.substring(0, indexOfFirstParenthesis);
        String rawParams = method.substring(indexOfFirstParenthesis + 1, method.length() - 1); // remove '('

        List<String> params = Arrays.asList(rawParams.split(","));

        Map<String, AllowedUrlType> paramsAndTypes = new HashMap<>();

        if (params.stream().filter(e -> ! e.isEmpty()).count() == 0){
            return new ClassPath(className, methodName, paramsAndTypes);
        }

        for (String param : params) {
            param = param.trim();
            int indexOfSep = param.indexOf(' ');
            if(indexOfSep == -1){
                throw new ConfigurationException("Malformed class name, ' ' is missing in parameter definition");
            }
            String type = param.substring(0, indexOfSep).trim();
            String name = param.substring(indexOfSep).trim();
            paramsAndTypes.put(name, AllowedUrlType.valueOf(type));
        }

        return new ClassPath(className, methodName, paramsAndTypes);

    }

}
