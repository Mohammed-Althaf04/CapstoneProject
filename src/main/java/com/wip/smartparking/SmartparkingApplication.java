package com.wip.smartparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Main Spring Boot application entrypoint that boots up the Smart Parking application.
 *
 * @author Naveen Muthu
 */

@SpringBootApplication
@EnableScheduling
public class SmartparkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartparkingApplication.class, args);
		System.out.println("App Started....!!");
	}

}
