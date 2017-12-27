package com.example.hello.welcome.impl;


import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.example.hello.welcome.api.GreetingMessage;
import com.example.hello.welcome.api.WelcomeService;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.server.ServerServiceCall;
import scala.concurrent.duration.FiniteDuration;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.completedFuture;

public class WelcomeServiceImpl implements WelcomeService {

    @Override
    public ServiceCall<NotUsed, String> welcome(String id) {

        return req -> completedFuture("hello " + id + "\n" + req);
    }

    @Override
    public ServiceCall<GreetingMessage, String> welcomePost(String id) {

        return req -> completedFuture("hello " + id + "\n" + req);
    }
    /*@Override
    public ServerServiceCall<String, Source<String, ?>> tick(int intervalMs) {
        return tickMessage -> {
            FiniteDuration interval = FiniteDuration.create(intervalMs, TimeUnit.MILLISECONDS);
            return CompletableFuture.completedFuture(Source.tick(interval, interval, tickMessage));
        };
    }

    @Override
    public ServerServiceCall<Source<String, ?>, Source<String, ?>> sayHello() {
        return names -> completedFuture(names.map(name -> "Hello " + name));
    }*/
}
/**
 * Can use any of the below method
 */
//return names -> completedFuture("Hello " + names);
// return names -> completedFuture(names.map(name -> "Hello " + name));
// return names -> CompletableFuture.completedFuture(names.map(name -> "Hello " + name));


/*
    @Override
    public ServiceCall<String, Source<String, ?>> welcomeExp(String id) {return tickMessage -> {
        // return req -> completedFuture("helloowooo");
        FiniteDuration interval = FiniteDuration.create(2, TimeUnit.MILLISECONDS);

        return completedFuture(Source.tick(interval, interval, tickMessage));
        // return names -> CompletableFuture.completedFuture(names.map(name -> "Hello " + name));

    };
    }*/

