package com.shorturl.rest;

import com.shorturl.coder.CoderSingleton;
import com.shorturl.services.CoderServices;
import com.shorturl.services.data.DataServiceSingleton;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class APIRedirect {
    private static final Logger LOGGER = LogManager.getLogger("APIRedirect");


    @GET
    @Path("{urlcode}")
    public static Response redirect(@PathParam("urlcode") String urlcode){
        final long startTime = System.currentTimeMillis();

        String safeUrlcode = StringEscapeUtils.escapeJava(urlcode);

        LOGGER.debug("Re-directing URL: input<" + safeUrlcode  +">");
        URI destinationURI = CoderServices.getURIFromCode(urlcode, DataServiceSingleton.DATA_SERVICE);

        final long endTime = System.currentTimeMillis();
        LOGGER.info("Total execution time: " + (endTime - startTime) );

        return Response.temporaryRedirect(destinationURI).build();
    }


    @GET
    @Path("/shortURL")
    public static String viewHomePage() {
      String formText = "<form action=\"generateCode\" method=\"post\"> <p> URL : <input type=\"text\" name=\"url\" /> </p><input type=\"submit\" value=\"Generate Code\" /></form>";
      return formText;
    }

    @POST
    @Path("generateCode")
    public  String generateCode(@FormParam("url") String url){
        String safeUrl = StringEscapeUtils.escapeJava(url);
        LOGGER.debug("SafeURL: " + safeUrl);
        try {
            return CoderServices.generateAndSaveCode(CoderSingleton.URL_CODER, DataServiceSingleton.DATA_SERVICE, new URI(safeUrl));
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
