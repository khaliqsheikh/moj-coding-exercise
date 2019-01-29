package com.coding.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.coding.example.model")
@ComponentScan("com.coding.example.controller")
@ComponentScan("com.coding.example.service")
@SpringBootApplication (scanBasePackages={"com.coding"})
@EnableJpaRepositories("com.coding.example.repository")
public class MojCodingExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MojCodingExerciseApplication.class, args);
	}

}



