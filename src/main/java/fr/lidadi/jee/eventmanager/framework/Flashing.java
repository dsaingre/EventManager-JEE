package fr.lidadi.jee.eventmanager.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Flash messages. You can either :
 *      - store a message that can be display only once
 *      - get the message to show and delete it.
 *
 * Useful to give information to user "Wrong credentials"
 */
public class Flashing {

    // To avoid to override data in session
    private static String sessionPrefix = "flashing_";

    // Static is required by jsp
    public static String consumeFlash(Map<String, Object> session, String key){
        String keyWithPrefix = sessionPrefix + key;
        String res = session.get(keyWithPrefix).toString();
        session.remove(keyWithPrefix);
        return res;
    }

    public static boolean flashExist(Map<String, Object> session, String key){
        String keyWithPrefix = sessionPrefix + key;
        return session.containsKey(keyWithPrefix);
    }



    public void flashing(HttpSession session, String key, String value){
        String keyWithPrefix = sessionPrefix + key;
        session.setAttribute(keyWithPrefix, value);
    }


}
