package com.shorturl.rest;


import com.shorturl.config.ConfigSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class ShortUrlAPIApplication extends ResourceConfig {
    private static final Logger LOGGER = LogManager.getLogger("Config");

    public ShortUrlAPIApplication() {
        packages("com.shorturl.rest");
        ConfigSingleton.CONFIG.printConfig(LOGGER);
    }
}

