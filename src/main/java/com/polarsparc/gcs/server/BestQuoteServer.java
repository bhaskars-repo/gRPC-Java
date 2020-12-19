/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BestQuoteServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(20003)
                .addService(new BestQuoteService())
                .build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Started the gRPC BestQuoteService on 20003 ...\n");

        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
