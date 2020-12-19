/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   28 Nov 2020
*/

package com.polarsparc.gun.client;

import com.polarsparc.gun.GreetRequest;
import com.polarsparc.gun.GreetResponse;
import com.polarsparc.gun.GreetServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreetClientTest {
    private GreetServiceGrpc.GreetServiceBlockingStub stub;

    @BeforeAll
    public void setup() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 20001)
                .usePlaintext()
                .build();

        this.stub = GreetServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void greetTest() {
        GreetRequest request = GreetRequest.newBuilder()
                .setName("Bob")
                .build();
        GreetResponse response = this.stub.greet(request);
        System.out.printf("%s\n", response.getMessage());
    }
}
