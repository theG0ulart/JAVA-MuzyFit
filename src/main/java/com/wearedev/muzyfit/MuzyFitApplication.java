package com.wearedev.muzyfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MuzyFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzyFitApplication.class, args);
	}

}
