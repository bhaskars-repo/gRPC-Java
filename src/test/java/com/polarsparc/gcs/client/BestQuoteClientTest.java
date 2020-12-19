/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.client;

import com.polarsparc.gcs.BestQuoteRequest;
import com.polarsparc.gcs.BestQuoteServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BestQuoteClientTest {
    private BestQuoteServiceGrpc.BestQuoteServiceStub stub;

    @BeforeAll
    public void setup() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 20003)
                .usePlaintext()
                .build();
        stub = BestQuoteServiceGrpc.newStub(channel);
    }

    @Test
    public void bestQuoteAsyncTestOne() {
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<BestQuoteRequest> requestObserver = stub.getBestQuote(new BestQuoteStreamObserver(latch));
        BestQuoteRequest req1 = BestQuoteRequest.newBuilder().setProvider("Bob").setAge(37).build();
        BestQuoteRequest req2 = BestQuoteRequest.newBuilder().setProvider("Charlie").setAge(37).build();
        requestObserver.onNext(req1);
        requestObserver.onNext(req2);
        requestObserver.onCompleted();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bestQuoteAsyncTestTwo() {
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<BestQuoteRequest> requestObserver = stub.getBestQuote(new BestQuoteStreamObserver(latch));
        BestQuoteRequest req1 = BestQuoteRequest.newBuilder().setProvider("Alice").setAge(48).build();
        BestQuoteRequest req2 = BestQuoteRequest.newBuilder().setProvider("Dave").setAge(48).build();
        requestObserver.onNext(req1);
        requestObserver.onNext(req2);
        requestObserver.onCompleted();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
