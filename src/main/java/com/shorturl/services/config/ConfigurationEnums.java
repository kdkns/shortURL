package com.shorturl.services.config;

public class ConfigurationEnums {

    public enum ATTRIBUTES{
        REDIS_URL("REDIS_URL"),
        REDIS_PORT("REDIS_PORT");

        private String key;

        public String key(){
            return this.key;
        }

        public String toSring(){
            return this.name();
        }

        ATTRIBUTES(String key){
            this.key = key;
        }
    }

}
