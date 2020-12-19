/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.client;

import com.polarsparc.gss.CurrencyRateRequest;
import com.polarsparc.gss.CurrencyRateResponse;
import com.polarsparc.gss.CurrencyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyRateClientTest {
    private CurrencyServiceGrpc.CurrencyServiceBlockingStub blockingStub;
    private CurrencyServiceGrpc.CurrencyServiceStub stub;

    @BeforeAll
    public void setup() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 20002)
                .usePlaintext()
                .build();
        this.blockingStub = CurrencyServiceGrpc.newBlockingStub(channel);
        this.stub = CurrencyServiceGrpc.newStub(channel);
    }

    @Test
    public void currencyRateBlockingTestOne() {
        CurrencyRateRequest request = CurrencyRateRequest.newBuilder()
                .setFrom("usd")
                .setTo("eur")
                .build();
        Iterator<CurrencyRateResponse> response = this.blockingStub.getCurrencyRate(request);
        response.forEachRemaining(res -> System.out.printf("Agent: %s, Rate: %.03f\n", res.getAgent(), res.getRate()));
    }

    @Test
    public void currencyRateBlockingTestTwo() {
        CurrencyRateRequest request = CurrencyRateRequest.newBuilder()
                .setFrom("eur")
                .setTo("jpy")
                .build();
        Iterator<CurrencyRateResponse> response = this.blockingStub.getCurrencyRate(request);
        Assertions.assertThrows(io.grpc.StatusRuntimeException.class, () -> response.forEachRemaining(res ->
                System.out.printf("Agent: %s, Rate: %.03f\n", res.getAgent(), res.getRate())));
    }

    @Test
    public void currencyRateAsyncTestOne() {
        CountDownLatch latch = new CountDownLatch(1);
        CurrencyRateRequest request = CurrencyRateRequest.newBuilder()
                .setFrom("usd")
                .setTo("cad")
                .build();
        this.stub.getCurrencyRate(request, new CurrencyRateStreamObserver(latch));
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
