package com.mastercard.connectivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectivityApplication {
	private static final Logger logger = LoggerFactory.getLogger(ConnectivityApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ConnectivityApplication.class, args);
	}

}
