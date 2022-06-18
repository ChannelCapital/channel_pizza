package com.project.commercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Application is deployed from this class with the main method.
 * @author fatih akguc
 * @version 1.0
 * @since 2022-06-19
 */

@SpringBootApplication
//@EnableMongoRepositories("com.project.commercial.repository")
public class CommercialWebsiteProjectApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CommercialWebsiteProjectApplication.class, args);
	}

}
