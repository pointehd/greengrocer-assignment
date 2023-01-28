package com.assignment.greengrocer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class GreengrocerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreengrocerApplication.class, args);
	}

}
