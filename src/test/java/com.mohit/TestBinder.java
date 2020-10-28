package com.mohit;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class TestBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(MyJerseyClientProvider.class).to(MyJerseyClientProvider.class);
        // i don't like this so i'm changing it
    }
}
