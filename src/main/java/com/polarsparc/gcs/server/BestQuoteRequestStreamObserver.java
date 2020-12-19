/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.server;

import com.polarsparc.gcs.BestQuoteRequest;
import com.polarsparc.gcs.BestQuoteResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BestQuoteRequestStreamObserver implements StreamObserver<BestQuoteRequest> {
    private final StreamObserver<BestQuoteResponse> response;
    private String provider;
    private double price;
    private boolean okay = true;

    public BestQuoteRequestStreamObserver(StreamObserver<BestQuoteResponse> response) {
        this.response = response;
        this.provider = null;
        this.price = 0.0;
    }

    @Override
    public void onNext(BestQuoteRequest request) {
        String provider = request.getProvider();
        int age = request.getAge();
        try {
            ProviderQuote pq = BestQuoteProvider.getBestQuote(provider, age);
            if (this.provider == null || this.price > pq.getPrice()) {
                this.provider = provider;
                this.price = pq.getPrice();
            }
        }
        catch (RuntimeException ex) {
            okay = false;
            onError(ex);
        }
    }

    @Override
    public void onError(Throwable ex) {
        Status status = Status.INVALID_ARGUMENT.withDescription(ex.getMessage());
        response.onError(status.asRuntimeException());
    }

    @Override
    public void onCompleted() {
        if (okay) {
            BestQuoteResponse quote = BestQuoteResponse.newBuilder()
                    .setProvider(this.provider)
                    .setPrice(this.price)
                    .build();
            response.onNext(quote);
            response.onCompleted();
        }
    }
}
