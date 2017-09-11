package com.mohit;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyJerseyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(MyJerseyClientProvider.class).to(MyJerseyClientProvider.class);
    }
}
