package com.shorturl.config;

import com.shorturl.services.config.ConfigServiceSingleton;

public class ConfigSingleton {
    public static final IConfig CONFIG = new Config(ConfigServiceSingleton.CONFIG_SERVICE);
}
