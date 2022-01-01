package com.doka.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan({"com.doka.controller", "com.doka.service",
		"com.doka.security"})
@EntityScan("com.doka.entity")
@EnableJpaRepositories("com.doka.repository")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProfessorAppApplication {

	public static void main(String[] args) {
		
		/*
		 * BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		 * String password1 = bCryptPasswordEncoder.encode("john123"); String password2
		 * = bCryptPasswordEncoder.encode("sachin123");
		 */
		
		SpringApplication.run(ProfessorAppApplication.class, args);
	}

}