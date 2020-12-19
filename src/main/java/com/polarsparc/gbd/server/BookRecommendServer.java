/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   19 Dec 2020
*/

package com.polarsparc.gbd.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BookRecommendServer {
    public static void main(String[] args) {
        int timeout = 100;
        if (args.length == 1) {
            timeout = Integer.parseInt(args[0]);
        }

        Server server = ServerBuilder.forPort(20004)
                .addService(new BookRecommendService(timeout))
                .build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Started the gRPC BookRecommendService on 20004 with timeout %d...\n", timeout);

        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
