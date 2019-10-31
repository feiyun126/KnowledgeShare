package com.zyb.netty.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NettyserverApplication {
    private static final int BEGIN_PORT = 8000;
    private static final int N_PORT = 100;
    public static void main(String[] args) {
        SpringApplication.run(NettyserverApplication.class, args);
        new Server().start(BEGIN_PORT, N_PORT);
    }
}
