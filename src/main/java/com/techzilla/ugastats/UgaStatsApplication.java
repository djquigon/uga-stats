package com.techzilla.ugastats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the UGAStats app, runs the UGAStat spring boot web app with all dependencies
 */
@SpringBootApplication
public class UgaStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UgaStatsApplication.class, args);
	}

}
