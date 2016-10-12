package fr.lidadi.jee.eventmanager.framework.router;

import fr.lidadi.jee.eventmanager.framework.router.config.Config;

/**
 * Created by damien on 08/10/2016.
 */
public interface HttpRouter {
    Config.HttpConfig route(Config.EmptyHttpConfig config);
}
