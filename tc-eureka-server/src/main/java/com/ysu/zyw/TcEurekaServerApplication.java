package com.ysu.zyw;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TcEurekaServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TcEurekaServerApplication.class).web(true).run(args);
    }

}
