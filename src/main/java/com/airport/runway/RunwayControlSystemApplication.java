package com.airport.runway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.airport.runway.services.RunwayServices;

@SpringBootApplication
public class RunwayControlSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RunwayControlSystemApplication.class, args);
	}
}
