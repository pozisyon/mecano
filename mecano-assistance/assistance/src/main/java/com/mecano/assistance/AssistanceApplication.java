package com.mecano.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AssistanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistanceApplication.class, args);
	}

}
