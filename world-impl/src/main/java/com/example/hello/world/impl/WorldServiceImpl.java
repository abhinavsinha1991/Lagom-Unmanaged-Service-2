package com.example.hello.world.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.server.ServerServiceCall;

import static java.util.concurrent.CompletableFuture.completedFuture;
import com.example.hello.world.api.WorldService;

public class WorldServiceImpl implements WorldService {

    @Override
    public ServerServiceCall<NotUsed, String> world(String id) {

        return req -> completedFuture("hello" + id);
    }

}
