package com.shorturl.config;

import org.apache.logging.log4j.Logger;

public interface IConfig {
    String getConfig(String key);
    void printConfig(Logger logger);
}
