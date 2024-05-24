package com.nttdata.BankAccountsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankAccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountsServiceApplication.class, args);
	}

}
