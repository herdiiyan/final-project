package com.sti.enigmabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@Import({ SpringConfig.class })
@EntityScan({ "com.sti.enigmabank.entity" })
@EnableJpaRepositories({ "com.sti.enigmabank.repository" })
@ComponentScan({ "com.sti.enigmabank" })
public class EnigmabankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmabankApplication.class, args);
	}

}
