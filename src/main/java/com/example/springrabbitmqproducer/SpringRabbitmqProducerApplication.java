package com.example.springrabbitmqproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRabbitmqProducerApplication {

	public static void main(String[] args) {
		System.out.print("application started succfully");
		SpringApplication.run(SpringRabbitmqProducerApplication.class, args);
	}

}
