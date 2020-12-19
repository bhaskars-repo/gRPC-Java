/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.client;

import com.polarsparc.gbd.BookRecommendRequest;
import com.polarsparc.gbd.BookRecommendServiceGrpc;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookRecommendClientTest {
    private ManagedChannel channel;

    @BeforeAll
    public void setup() {
        channel = ManagedChannelBuilder.forAddress("localhost", 20004)
                .usePlaintext()
                .build();
    }

    @Test
    public void bookRecommendAsyncTestOne() {
        CountDownLatch latch = new CountDownLatch(1);
        BookRecommendServiceGrpc.BookRecommendServiceStub stub = BookRecommendServiceGrpc.newStub(channel);
        StreamObserver<BookRecommendRequest> requestObserver = stub.recommendedBooks(
                new BookRecommendStreamObserver(latch));
        BookRecommendRequest req1 = BookRecommendRequest.newBuilder().setTopic("go").build();
        BookRecommendRequest req2 = BookRecommendRequest.newBuilder().setTopic("Python").build();
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
    public void bookRecommendAsyncTestTwo() {
        CountDownLatch latch = new CountDownLatch(1);
        BookRecommendServiceGrpc.BookRecommendServiceStub stub = BookRecommendServiceGrpc.newStub(channel);
        StreamObserver<BookRecommendRequest> requestObserver = stub.recommendedBooks(
                new BookRecommendStreamObserver(latch));
        BookRecommendRequest req1 = BookRecommendRequest.newBuilder().setTopic("java").build();
        BookRecommendRequest req2 = BookRecommendRequest.newBuilder().setTopic("rust").build();
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
    public void bookRecommendAsyncTestThree() {
        CountDownLatch latch = new CountDownLatch(1);
        BookRecommendServiceGrpc.BookRecommendServiceStub stub =
                BookRecommendServiceGrpc.newStub(channel)
                .withDeadline(Deadline.after(550, TimeUnit.MILLISECONDS));
        StreamObserver<BookRecommendRequest> requestObserver = stub.recommendedBooks(
                new BookRecommendStreamObserver(latch));
        BookRecommendRequest req1 = BookRecommendRequest.newBuilder().setTopic("java").build();
        BookRecommendRequest req2 = BookRecommendRequest.newBuilder().setTopic("python").build();
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
