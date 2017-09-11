package com.mohit;

import org.jvnet.hk2.annotations.Service;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ws.rs.client.ClientBuilder;




@Service
public class MyJerseyClientProvider  {
    public MyJerseyClientService returnMyJerseyClient(){
        return new MyJerseyClientService(ClientBuilder.newClient());
    }
}
