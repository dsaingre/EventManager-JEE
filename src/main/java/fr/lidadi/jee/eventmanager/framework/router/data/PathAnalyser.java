package fr.lidadi.jee.eventmanager.framework.router.data;

import java.util.LinkedList;
import java.util.List;

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
}
