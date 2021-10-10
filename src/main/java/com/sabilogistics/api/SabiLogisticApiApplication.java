package com.sabilogistics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.sabi")
@EntityScan(basePackages = {"com.sabilogisticscore.models"})
@SpringBootApplication
public class SabiLogisticApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabiLogisticApiApplication.class, args);
	}

}
