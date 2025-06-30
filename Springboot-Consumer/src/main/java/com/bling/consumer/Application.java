package com.bling.consumer;

import com.bling.rpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRpc
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
