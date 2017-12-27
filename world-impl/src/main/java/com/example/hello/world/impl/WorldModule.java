package com.example.hello.world.impl;

import com.example.hello.world.api.WorldService;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class WorldModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(WorldService.class, WorldServiceImpl.class);

  /*protected void configure() {
    bindServices(serviceBinding(WelcomeService.class, WelcomeServiceImpl.class));*/
    }
}