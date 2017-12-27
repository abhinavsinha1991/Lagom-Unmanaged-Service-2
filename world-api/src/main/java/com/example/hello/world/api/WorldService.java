package com.example.hello.world.api;
import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.Service;

public interface WorldService extends Service {

    ServiceCall<NotUsed, String> world(String id);

    @Override
    default Descriptor descriptor() {
        return named("world").withCalls(pathCall("/world/:id", this::world))
                .withAutoAcl(true);
    }
}

