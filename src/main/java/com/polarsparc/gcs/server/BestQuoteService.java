/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.server;

import com.polarsparc.gcs.BestQuoteRequest;
import com.polarsparc.gcs.BestQuoteResponse;
import com.polarsparc.gcs.BestQuoteServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BestQuoteService extends BestQuoteServiceGrpc.BestQuoteServiceImplBase {
    private final static Logger LOGGER = Logger.getLogger(BestQuoteService.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public StreamObserver<BestQuoteRequest> getBestQuote(StreamObserver<BestQuoteResponse> responseObserver) {
        return new BestQuoteRequestStreamObserver(responseObserver);
    }
}
