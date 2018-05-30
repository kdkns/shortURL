package com.shorturl.services;

import com.shorturl.services.data.IShortURLDataServices;
import com.shorturl.coder.URLCoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class CoderServices {
    private static final Logger LOGGER = LogManager.getLogger("CoderServices");
    private static final String ERROR_PAGE ="http://www.google.com";

    public static URI getURIFromCode(String urlcode, IShortURLDataServices dataServices) {


        try {
            String uri = dataServices.getURIAddressFromCode(urlcode);

            if(uri != null){
                return new URI(uri);
            }else{
                return new URI(ERROR_PAGE);
            }

        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
            return null;
        }


    }

    public static String generateAndSaveCode(URLCoder urlCoder, IShortURLDataServices dataServices, URI uri){
        String encodedKey = urlCoder.encodeURL(uri, dataServices);
        dataServices.saveShortUrl(uri, encodedKey);
        //dataServices.printAllData(LOGGER);
        return encodedKey;
    }
}
