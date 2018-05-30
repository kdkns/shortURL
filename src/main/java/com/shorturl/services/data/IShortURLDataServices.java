package com.shorturl.services.data;

import org.apache.logging.log4j.Logger;

import java.net.URI;

public interface IShortURLDataServices {
    String getURIAddressFromCode(String urlcode);
    void saveShortUrl(URI uri, String encodedKey);
    void printAllData(Logger logger);
    long getSalt();
}
