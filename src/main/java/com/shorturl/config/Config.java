package com.shorturl.config;

import com.shorturl.services.config.IConfigService;
import org.apache.logging.log4j.Logger;

public class Config extends AbstractConfig{


    public Config(IConfigService configService){
        super(configService);

    }


    @Override
    public void printConfig(Logger logger) {
        for(String key : getConfigurationMap().keySet()){
            logger.debug(key + " : " + getConfigurationMap().get(key));
        }
    }
}
