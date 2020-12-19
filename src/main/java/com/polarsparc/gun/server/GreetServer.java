/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   28 Nov 2020
*/

package com.polarsparc.gun.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GreetServer {
    public static void main(String[] args) {
        Server server = NettyServerBuilder
                .forAddress(new InetSocketAddress("127.0.0.1", 20001))
                .addService(new GreetService())
                .build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Started the gRPC GreetService on 127.0.0.1:20001 ...\n");

        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
