package com.shorturl.services.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RedisShortURLDataServiceTest {
    private static final Logger LOGGER = LogManager.getLogger("RedisShortURLDataServiceTest");

    @org.junit.jupiter.api.Test
    void verifySaveURLAndGetFromCode() {
        final long startTime = System.currentTimeMillis();

        try {
            DataServiceSingleton.DATA_SERVICE.saveShortUrl(new URI("http://www.google.com"), "AAAAAAA");
            String url = DataServiceSingleton.DATA_SERVICE.getURIAddressFromCode("AAAAAAA");
            assertTrue(url.equalsIgnoreCase("http://www.google.com"));
        } catch (URISyntaxException e) {
           LOGGER.error(e);
        }
        final long endTime = System.currentTimeMillis();

        LOGGER.info("Total execution time: " + (endTime - startTime) );
    }


    @org.junit.jupiter.api.Test
    void printAllData() {
        DataServiceSingleton.DATA_SERVICE.printAllData(LOGGER);
    }

    @org.junit.jupiter.api.Test
    void getSalt() {
        DataServiceSingleton.DATA_SERVICE.getSalt();
    }
}