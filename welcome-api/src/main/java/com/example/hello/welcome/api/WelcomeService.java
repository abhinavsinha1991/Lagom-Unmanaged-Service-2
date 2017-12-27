package com.example.hello.welcome.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;
//import com.lightbend.lagom.javadsl.server.ServerServiceCall;

import com.lightbend.lagom.javadsl.api.Service;

public interface WelcomeService extends Service {

    ServiceCall<NotUsed, String> welcome(String id);

    ServiceCall<GreetingMessage, String> welcomePost(String id);

  //  ServiceCall<String, Source<String, ?>> tick(int interval);

    //ServiceCall<Source<String, ?>, Source<String, ?>> sayHello();

    @Override
    default Descriptor descriptor() {
        return named("welcome").withCalls(pathCall("/welcome/:id", this::welcome),
                pathCall("/welcomepost/:id", this::welcomePost)/*,
                pathCall("/tick/:interval", this::tick),
                pathCall("/sayhello", this::sayHello)*/
        )
                .withAutoAcl(true);
    }
}
