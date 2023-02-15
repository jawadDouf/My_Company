package com.example.companyArchetictureService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CompanyArchetictureServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CompanyArchetictureServiceApplication.class, args);

	}

}
