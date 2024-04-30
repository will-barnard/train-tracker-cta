package com.barnard.traintrackerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaCtaTrainTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCtaTrainTrackerApplication.class, args);
	}

}
