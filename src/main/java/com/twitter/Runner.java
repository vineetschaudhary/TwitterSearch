package com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstrap class to run Twitter Search service.
 * 
 * @author Veenit Kumar
 * @since 10-12-2016
 */
@SpringBootApplication
public class Runner {
	/**
	 * Main method to be called first and start the embedded services automatically.
	 * 
	 * @param args - Runtime parameters.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}
}
