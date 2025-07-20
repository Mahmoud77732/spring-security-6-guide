package com.hegazy.ssecuritypart6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
/*
@EnableJpaRepositories("com.hegazy.ssecuritypart6.repo")
@EntityScan("com.hegazy.ssecuritypart6.model")
*/
// Optional
@EnableWebSecurity
public class Ssecuritypart6Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssecuritypart6Application.class, args);
	}

}
