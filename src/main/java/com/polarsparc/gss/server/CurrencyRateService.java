/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.server;

import com.polarsparc.gss.CurrencyRateRequest;
import com.polarsparc.gss.CurrencyRateResponse;
import com.polarsparc.gss.CurrencyServiceGrpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyRateService extends CurrencyServiceGrpc.CurrencyServiceImplBase {
    private final static Logger LOGGER = Logger.getLogger(CurrencyRateService.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void getCurrencyRate(CurrencyRateRequest request, StreamObserver<CurrencyRateResponse> responseObserver) {
        String from = request.getFrom();
        String to = request.getTo();

        List<CurrencyRate> rates;
        try {
            rates = CurrencyRateProvider.getCurrencyRate(from, to);
        }
        catch (RuntimeException ex) {
            Status status = Status.FAILED_PRECONDITION.withDescription(ex.getMessage());
            responseObserver.onError(status.asRuntimeException());
            return;
        }

        if (rates != null) {
            LOGGER.info(String.format("Rates from agents: %s", rates));

            rates.forEach(r -> {
                CurrencyRateResponse response = CurrencyRateResponse.newBuilder()
                        .setAgent(r.getAgent())
                        .setFrom(from)
                        .setTo(to)
                        .setRate(r.getRate())
                        .build();
                responseObserver.onNext(response);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            responseObserver.onCompleted();
        }
    }
}
