package com.microservice.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroserviceCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCreditApplication.class, args);
	}
}
