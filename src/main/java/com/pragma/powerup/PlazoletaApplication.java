package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlazoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlazoletaApplication.class, args);
	}

}
