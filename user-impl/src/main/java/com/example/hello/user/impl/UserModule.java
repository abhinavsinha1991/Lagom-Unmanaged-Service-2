package com.example.hello.user.impl;

import com.example.hello.user.api.ExternalService;
import com.example.hello.user.api.UserService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.api.ServiceLocator;
import com.lightbend.lagom.javadsl.client.ConfigurationServiceLocator;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import play.Configuration;
import play.Environment;

public class UserModule extends AbstractModule implements ServiceGuiceSupport {

    private final Environment environment;
    private final Configuration configuration;  //NOSONAR as this is required field.

    public UserModule(Environment environment, Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bindService(UserService.class, UserServiceImpl.class);

        if (!environment.isTest()) {
            bindClient(ExternalService.class);
        }


    }
}
