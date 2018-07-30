package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.bookstore.repository") 
@EntityScan("com.bookstore.entity")
@SpringBootApplication
public class SimpleBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBookstoreApplication.class, args);
	}
}
