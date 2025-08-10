package com.hegazy.ssecuritypart26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class Ssecuritypart26Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssecuritypart26Application.class, args);
	}

}
