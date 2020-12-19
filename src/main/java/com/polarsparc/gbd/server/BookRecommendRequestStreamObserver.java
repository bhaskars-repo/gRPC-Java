/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.server;

import com.polarsparc.gbd.BookRecommendRequest;
import com.polarsparc.gbd.BookRecommendResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRecommendRequestStreamObserver implements StreamObserver<BookRecommendRequest> {
    private final static Logger LOGGER = Logger.getLogger(BookRecommendRequestStreamObserver.class.getName());

    private final StreamObserver<BookRecommendResponse> response;
    private final int timeout;
    private boolean flag = true;

    public BookRecommendRequestStreamObserver(int timeout, StreamObserver<BookRecommendResponse> response) {
        LOGGER.setLevel(Level.INFO);

        this.timeout = timeout;
        this.response = response;

        LOGGER.info(String.format("Timeout value: %d\n", timeout));
    }

    @Override
    public void onNext(BookRecommendRequest request) {
        try {
            List<Book> books = BooksCatalog.getBooks(request.getTopic());
            for (Book bk : books) {
                BookRecommendResponse res = BookRecommendResponse.newBuilder()
                        .setTopic(request.getTopic())
                        .setTitle(bk.getTitle())
                        .setIsbn(bk.getISBN())
                        .build();
                response.onNext(res);
                try {
                    Thread.sleep(timeout);
                }
                catch (InterruptedException ignored) {
                }
            }
        }
        catch (RuntimeException ex) {
            onError(ex);
        }
    }

    @Override
    public void onError(Throwable ex) {
        Status status = Status.fromThrowable(ex);
        if (status.getCode() == Status.CANCELLED.getCode()) {
            LOGGER.severe("ERROR - Deadline breached by BookRecommendService at localhost:20004");
        } else {
            LOGGER.severe(ex.getMessage());
        }
        response.onError(status.asRuntimeException());
        flag = false;
    }

    @Override
    public void onCompleted() {
        if (flag) {
            response.onCompleted();
        }
    }
}
