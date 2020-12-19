/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.client;

import com.polarsparc.gss.CurrencyRateResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class CurrencyRateStreamObserver implements StreamObserver<CurrencyRateResponse> {
    private final CountDownLatch latch;

    public CurrencyRateStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(CurrencyRateResponse response) {
        System.out.printf("Agent: %s, Rate: %.03f\n", response.getAgent(), response.getRate());
    }

    @Override
    public void onError(Throwable ex) {
        System.out.println("Exception: " + ex.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Done !!!");
        latch.countDown();
    }
}
