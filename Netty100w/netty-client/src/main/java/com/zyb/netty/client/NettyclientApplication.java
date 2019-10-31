package com.zyb.netty.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyclientApplication {
    private static final int BEGIN_PORT = 8000;
    private static final int N_PORT = 100;
    public static void main(String[] args) {
        SpringApplication.run(NettyclientApplication.class, args);
        new Client().start(BEGIN_PORT, N_PORT);
    }
}
