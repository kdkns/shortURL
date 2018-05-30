package com.shorturl.coder;

import com.shorturl.services.data.IShortURLDataServices;

import java.net.URI;

public class URLCoder extends AbstractURLCoder {

    protected URLCoder(){};

    private String generateCode(URI uri, IShortURLDataServices dataServices){

        long salt = dataServices.getSalt();
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(uri.toString() + salt);
        String code = sha256hex.substring(0, 7);

        return code;
    }

    @Override
    public String encodeURL(URI uri, IShortURLDataServices dataServices) {

        String code;
        String foundCode;

        //Retry generating code until unique code is found
        do{
            code = generateCode(uri, dataServices);
            foundCode = dataServices.getURIAddressFromCode(code);

        } while(foundCode != null);

        return code;
    }
}
