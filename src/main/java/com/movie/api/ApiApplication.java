package com.movie.api;

import com.movie.api.db.Database;
import com.movie.api.db.DatabaseSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		DatabaseSetup databaseSetup = new DatabaseSetup();
	}
}
