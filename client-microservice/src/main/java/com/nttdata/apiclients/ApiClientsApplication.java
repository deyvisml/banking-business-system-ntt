package com.nttdata.apiclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientsApplication.class, args);
	}
}
