package com.shorturl.services.config;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemEnvironmentConfigService implements IConfigService{
    private static final Logger LOGGER = LogManager.getLogger("SystemEnvironmentConfigService");

    protected SystemEnvironmentConfigService(){};

    @Override
    public String getConfig(String key) {
        LOGGER.debug("System Environment Config Key: " + key + ":" + StringEscapeUtils.escapeJava(key));
        return System.getenv(key);
    }
}
