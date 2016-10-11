package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.router.config.EmptyHttpConfig;
import fr.lidadi.jee.eventmanager.framework.router.config.HttpConfig;

/**
 * Created by damien on 08/10/2016.
 */
public interface HttpRouter {
    HttpConfig route(EmptyHttpConfig config);
}
