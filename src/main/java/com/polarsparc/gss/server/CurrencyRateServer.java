/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CurrencyRateServer {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(20002)
                .addService(new CurrencyRateService())
                .build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Started the gRPC CurrencyRateService on 20002 ...\n");

        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
