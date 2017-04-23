package com.ysu.zyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TcEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcEurekaClientApplication.class, args);
    }

}
