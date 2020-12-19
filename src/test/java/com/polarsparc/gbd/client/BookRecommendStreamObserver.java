/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.client;

import com.polarsparc.gbd.BookRecommendResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRecommendStreamObserver implements StreamObserver<BookRecommendResponse> {
    private final static Logger LOGGER = Logger.getLogger(BookRecommendStreamObserver.class.getName());

    private final CountDownLatch latch;

    static {
        LOGGER.setLevel(Level.INFO);
    }

    public BookRecommendStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(BookRecommendResponse response) {
        LOGGER.info(String.format("Recommended book => topic: %s, title: %s, isbn: %s\n",
                response.getTopic(), response.getTitle(), response.getIsbn()));
    }

    @Override
    public void onError(Throwable ex) {
        Status status = Status.fromThrowable(ex);
        LOGGER.severe(String.format("Error status: code - %s, description - %s\n", status.getCode(), status.getDescription()));
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        latch.countDown();
    }
}
