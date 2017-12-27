package com.example.hello.user.impl;

import akka.NotUsed;

import com.example.hello.user.api.ExternalService;
import com.example.hello.user.api.UserService;
import com.example.hello.user.mapper.ResponseMapper;
import com.example.hello.user.models.UserData;
import com.example.hello.user.models.UserResponse;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.concurrent.CompletionStage;


public class UserServiceImpl implements UserService {

    ExternalService externalService;

    ResponseMapper responseMapper;

    @Inject
    public UserServiceImpl(ExternalService externalService, ResponseMapper responseMapper) {

        this.externalService = externalService;
        this.responseMapper = responseMapper;
    }

    public CompletionStage<UserData> hitAPI() {
        System.out.println("hitAPI main");
        CompletionStage<UserData> userDataCompletionStage =  externalService
                .getUser()
                .invoke();
        userDataCompletionStage.thenApply(userInfo ->{
            System.out.println(userInfo.body);
            return null;
        });
        return userDataCompletionStage;
    }

    @Override
    public ServiceCall<NotUsed, UserResponse> helloUser() {
        return request ->
        {
            System.out.println("helloUser 1");
            CompletionStage<UserData> userData = hitAPI();
            System.out.println("helloUser 2");
            System.out.println(userData);
            System.out.println("helloUser 3");

            return userData.thenApply(
                    userInfo ->
                    {
                        System.out.println("then apply " + userInfo.userId);
                        UserResponse userResponse =  responseMapper.getResponse(userInfo);

                        System.out.println("then apply ");
                        System.out.println("then apply " + userResponse);

                        return userResponse;
                    }
            );/*
            CompletionStage<UserResponse> userResponse = userData.thenComposeAsync(data->responseMapper.getResponse(data));
            System.out.println("helloUser 3");
            return userResponse;*/
        };
    }

}
