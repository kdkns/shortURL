package com.shorturl.config;


import com.shorturl.services.config.ConfigurationEnums;
import com.shorturl.services.config.IConfigService;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfig implements IConfig {

    private  IConfigService configService;

    protected Map<String, String> configurationMap = new HashMap<>();



    protected AbstractConfig(IConfigService configService){
        setConfigService(configService);
        for(ConfigurationEnums.ATTRIBUTES config : ConfigurationEnums.ATTRIBUTES.values()){
            getConfig(config.key());
        }
    }


    protected Map<String, String> getConfigurationMap() {
        return configurationMap;
    }

    protected void setConfigurationMap(Map<String, String> configurationMap) {
        this.configurationMap = configurationMap;
    }


    protected IConfigService getConfigService() {
        return configService;
    }

    protected void setConfigService(IConfigService configService) {
        this.configService = configService;
    }



    @Override
    public String getConfig(String key) {
        String result = getConfigurationMap().get(key);
        if(result != null){
            return result;
        }else{
            result = getConfigService().getConfig(key);
            getConfigurationMap().put(key, result);
        }
        return result;
    }

}
