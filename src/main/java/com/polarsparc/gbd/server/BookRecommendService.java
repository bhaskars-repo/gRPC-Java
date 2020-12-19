/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.server;

import com.polarsparc.gbd.BookRecommendRequest;
import com.polarsparc.gbd.BookRecommendResponse;
import com.polarsparc.gbd.BookRecommendServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BookRecommendService extends BookRecommendServiceGrpc.BookRecommendServiceImplBase {
    private final int timeout;

    public BookRecommendService(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public StreamObserver<BookRecommendRequest> recommendedBooks(StreamObserver<BookRecommendResponse> responseObserver) {
        return new BookRecommendRequestStreamObserver(timeout, responseObserver);
    }
}
