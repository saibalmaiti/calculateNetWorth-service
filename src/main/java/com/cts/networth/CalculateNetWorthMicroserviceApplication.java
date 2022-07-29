package com.cts.networth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CalculateNetWorthMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculateNetWorthMicroserviceApplication.class, args);
	}

}
