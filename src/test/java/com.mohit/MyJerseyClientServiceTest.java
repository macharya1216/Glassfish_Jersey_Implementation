package com.mohit;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyJerseyClientServiceTest {

    private final String urlToTest = "https://www.google.com";
    @Test
    public void  testRegularClientTest() throws URISyntaxException {

        MyJerseyClientService myJerseyClientService = new MyJerseyClientService(ClientBuilder.newClient());
        MultipleObjectHolder<String,Integer> map = myJerseyClientService.sendResponse(urlToTest);
        assertTrue(map != null);
        assertTrue(map.a != null && map.b != null);
        System.out.println("Response is "+ map.a +"and status code is" +map.b );

    }

    @Test
    public void  testMockedClientTest() throws URISyntaxException {

        Client client = mock(Client.class);
        WebTarget webTarget = mock(WebTarget.class);
        Invocation.Builder ib = mock(Invocation.Builder.class);
        when(client.target(any(URI.class))).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(ib);
        Response mockWebResponse = mock(Response.class);
        when(mockWebResponse.getStatus()).thenReturn(201);
        when(mockWebResponse.readEntity(String.class)).thenReturn("test");
        when(ib.get()).thenReturn(mockWebResponse);

        MyJerseyClientService myJerseyClientService = new MyJerseyClientService(client);
        MultipleObjectHolder<String,Integer> map = myJerseyClientService.sendResponse(urlToTest);

        assertTrue(map != null);
        assertTrue(map.a != null && map.b != null);
        System.out.println("Response is "+ map.a +"and status code is" +map.b );

    }
}
