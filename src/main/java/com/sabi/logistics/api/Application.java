package com.sabi.logistics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "com.sabi.framework")
@EntityScan(basePackages = {"com.sabi.logistics.core.models"})
@EnableJpaRepositories({"com.sabi.logistics.service.repositories","com.sabi.framework.repositories"})
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);



	}

}
