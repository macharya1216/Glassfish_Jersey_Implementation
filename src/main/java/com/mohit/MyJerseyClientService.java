package com.mohit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

public class MyJerseyClientService {

    private Client httpClient;

    MyJerseyClientService(Client httpClient){
        this.httpClient = httpClient;
    }

    MultipleObjectHolder<String,Integer> sendResponse(String url) throws URISyntaxException {
        System.out.println("Requesting Response from MyJerseyClient Service");
        WebTarget webTarget = httpClient.target(new URI(url));
        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        return new MultipleObjectHolder<String,Integer>(response.readEntity(String.class),response.getStatus());
    }


}
