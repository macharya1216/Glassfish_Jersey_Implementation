package com.mohit;

import org.glassfish.jersey.server.ResourceConfig;

public class MyJerseyApplication extends ResourceConfig {
    public MyJerseyApplication() {
        register(new MyJerseyApplicationBinder());
        packages(true, "com.mohit");
    }
}
