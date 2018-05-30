package com.shorturl.services.config;

public class ConfigServiceSingleton {
    public static final IConfigService CONFIG_SERVICE = new SystemEnvironmentConfigService();
}
