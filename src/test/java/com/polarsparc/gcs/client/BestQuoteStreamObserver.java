/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.client;

import com.polarsparc.gcs.BestQuoteResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class BestQuoteStreamObserver implements StreamObserver<BestQuoteResponse> {
    private final CountDownLatch latch;

    public BestQuoteStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(BestQuoteResponse response) {
        System.out.printf("Best quote from provider %s with price %.02f\n",
                response.getProvider(), response.getPrice());
    }

    @Override
    public void onError(Throwable ex) {
        Status status = Status.fromThrowable(ex);
        System.out.printf("Error status: code - %s, description - %s\n", status.getCode(), status.getDescription());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Done !!!");
        latch.countDown();
    }
}
