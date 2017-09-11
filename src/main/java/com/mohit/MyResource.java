package com.mohit;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

/**
 * Root resource (exposed at "myresource" path)
 */

@Path("myresource")
public class MyResource {

    @Inject
    private MyJerseyClientProvider myJerseyClientProvider;


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/url-usejersey/{urlToUse : .+}")
    public String  getIt(@PathParam("urlToUse") String urlToUse) throws URISyntaxException {
        System.out.println("URL Recieved from client "+urlToUse);
        System.out.println("Provider is : "+myJerseyClientProvider.getClass());
       String response = null;
        try {
            response =   myJerseyClientProvider.returnMyJerseyClient().sendResponse(urlToUse).a;
        }catch(Exception e){
            System.out.println("Exception while authenticating with the client "+e);
        }

    return response;
    }

}
