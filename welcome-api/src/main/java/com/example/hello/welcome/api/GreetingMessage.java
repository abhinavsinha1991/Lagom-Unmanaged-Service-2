package com.example.hello.welcome.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;

public final class GreetingMessage {

    public final String message;

    @JsonCreator
    public GreetingMessage(String message) {
        this.message = Preconditions.checkNotNull(message, "message");
    }
}

