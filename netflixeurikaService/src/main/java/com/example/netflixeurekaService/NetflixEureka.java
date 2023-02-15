package com.example.netflixeurekaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEureka {
    public static void main(String[] args) {
        SpringApplication.run(NetflixEureka.class, args);
    }
}
