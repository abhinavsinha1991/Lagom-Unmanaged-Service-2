package com.example.hello.user.impl.UnitTest;
import akka.NotUsed;
import com.example.hello.user.models.UserResponse;
import com.example.hello.user.api.ExternalService;
import com.example.hello.user.impl.UserServiceImpl;
import com.example.hello.user.mapper.ResponseMapper;
import com.example.hello.user.models.UserData;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import mockit.*;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class UserServiceImplTest {

    private static final Integer ID = 1;
    private static final Integer USERID = 1;
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static UserData newUserData = new UserData() {
        {
            setId(ID);
            setUserId(USERID);
            setTitle(TITLE);
            setBody(BODY);
        }
    };
    //@Mocked({"hitAPI"})

    @Tested
    private UserServiceImpl userServiceImpl;

    @Injectable
    private ExternalService externalService;

    @Injectable
    private ResponseMapper responseMapper;

    @Test
    public void helloUserTest() throws Exception {
        new Expectations() {
            {
                ServiceCall<NotUsed, UserData> demoCall = new ServiceCall<NotUsed, UserData>() {
                    @Override
                    public CompletionStage<UserData> invoke(NotUsed notUsed) {
                        return CompletableFuture.completedFuture(newUserData);
                    }
                };
                System.out.println("in exp");
                externalService.getUser();
                result = new ServiceCall<NotUsed, UserData>() {
                    @Override
                    public CompletionStage<UserData> invoke(NotUsed notUsed) {
                        return CompletableFuture.completedFuture(newUserData);
                    }
                };
            }

            {
                responseMapper.getResponse(newUserData);
                result = new UserResponse() {
                    {setMessage("Hello, Your UserId is " + newUserData.userId);
                    }
                };
            }
        };
        /*new MockUp<ExternalService>() {
            @SuppressWarnings("unused")
            @Mock
            ServiceCall<NotUsed, UserData> getUser(){
                System.out.println("getUser");

                return ( new ServiceCall<NotUsed, UserData>() {
                    @Override
                    public CompletionStage<UserData> invoke(NotUsed notUsed) {
                        return CompletableFuture.completedFuture(newUserData);
                    }
                });
            }
        };*/
  /*      new MockUp<ExternalService>() {
            @SuppressWarnings("unused")
            @Mock
            ServiceCall<NotUsed,UserData> getUser() {
                System.out.println("getUser");
                return (new ServiceCall<NotUsed, UserData>() {
                    @Override
                    public CompletionStage<UserData> invoke(NotUsed notUsed) {
                        return CompletableFuture.completedFuture(newUserData);
                    }
                }
                );
            }
        };

        new MockUp<ResponseMapper>() {
            @SuppressWarnings("unused")
            @Mock
            public UserResponse getResponse( UserData userData) {
                System.out.println("hitAPI");
                return (new UserResponse() {
                                                             {setMessage("Hello, Your UserId is " + newUserData.userId);
                                                             }
                                                         }
                );
            }
        };
*/


       /* new Expectations() {
            *//*{
                userServiceImpl.hitAPI();
                returns(CompletableFuture.completedFuture(newuserData));
            }*//*

            {
                responseMapper.getResponse(userData);
                returns(CompletableFuture.completedFuture(
                        new UserResponse(){{setMessage("Hello, Your UserId is " + userData.userId);}}
-
                ));
            }
        };*/
        UserResponse receivedResponse = userServiceImpl
                .helloUser()
                .invoke()
                .toCompletableFuture().get(5, SECONDS);

        System.out.println(receivedResponse);
        assertEquals("helloUser method fails ", "Hello, Your UserId is " + ID, receivedResponse.getMessage());
    }
}