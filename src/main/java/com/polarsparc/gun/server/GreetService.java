/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   28 Nov 2020
*/

package com.polarsparc.gun.server;

import com.polarsparc.gun.GreetRequest;
import com.polarsparc.gun.GreetResponse;
import com.polarsparc.gun.GreetServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.time.LocalTime;

public class GreetService extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        String message = getMessage(request.getName());

        GreetResponse response = GreetResponse.newBuilder()
                .setMessage(message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static String getMessage(String name) {
        LocalTime lt = LocalTime.now();

        int hour = lt.getHour();

        StringBuilder sb = new StringBuilder("Hello ").append(name).append(", ");
        if (hour < 12) {
            sb.append("Good Morning !!!");
        } else if (hour < 16) {
            sb.append("Good Afternoon !!!");
        } else if (hour < 21) {
            sb.append("Good Evening !!!");
        } else {
            sb.append("Good Night !!!");
        }

        return sb.toString();
    }
}

