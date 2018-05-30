package com.shorturl.services.data;

public class DataServiceSingleton {
    public static final IShortURLDataServices DATA_SERVICE = new RedisShortURLDataService();
}
