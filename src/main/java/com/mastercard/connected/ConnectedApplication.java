package com.mastercard.connected;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectedApplication {
	private static final Logger logger = LoggerFactory.getLogger(ConnectedApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ConnectedApplication.class, args);
	}

}
