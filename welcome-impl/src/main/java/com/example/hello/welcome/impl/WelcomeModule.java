package com.example.hello.welcome.impl;

import com.example.hello.welcome.api.WelcomeService;
//import com.example.hello.world.api.WorldService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
/*import com.example.hello.welcome.api.WelcomeService;

public class WelcomeModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(WelcomeService.class, WelcomeServiceImpl.class);
      //  bindClient(WelcomeService.class);

    }

}*/

public class WelcomeModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {

        bindService(WelcomeService.class, WelcomeServiceImpl.class);
     //   bindClient(WorldService.class);
    }
}

