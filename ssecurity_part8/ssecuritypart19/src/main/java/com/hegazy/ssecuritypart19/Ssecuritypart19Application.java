package com.hegazy.ssecuritypart19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class Ssecuritypart19Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssecuritypart19Application.class, args);
	}

}
