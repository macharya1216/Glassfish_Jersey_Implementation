package com.mohit;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyResourceTest  extends JerseyTest{

    static final private String clientUrl = "https://www.google.com";

    @Mock
    MyJerseyClientProvider myJerseyClientProvider;


    @Override
    public Application configure() {

        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        MockitoAnnotations.initMocks(this);
        MyJerseyClientService jerseyClientService = mock(MyJerseyClientService.class);
         MultipleObjectHolder<String, Integer> tuple = new MultipleObjectHolder("test",200);
        try {
            when(jerseyClientService.sendResponse(anyString())).thenReturn(tuple);
        } catch (URISyntaxException e) {
            System.out.println("Exception caught while mocking jersey client server {}"+e);
        }
        when(myJerseyClientProvider.returnMyJerseyClient()).thenReturn(jerseyClientService);
        return new ResourceConfig(MyResource.class).register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(myJerseyClientProvider).to(MyJerseyClientProvider.class);
            }
        }).register(LoggingFeature.class);
    }

    @Test
    public void testFetchAll() {
        Response output = target("/myresource/url-usejersey/urlToUse/https://www.google.com").request().get();
        assertEquals("should return status 200", 200, output.getStatus());


    }

}
