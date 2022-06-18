package com.project.commercial.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.databind.util.Converter;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * This is a mongo configuration class. Client, mapping and name of the database is determined here.
 * @author fatih akguc
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.project.commercial")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

    @Override
    protected String getDatabaseName() {
        return "pizzaorder";
    }

    @Override
    public MongoClient mongoClient() {
    	/** For local mongodb compass usage the connection string is "mongodb://localhost:27017/pizzaorder"
    	 *This "mongo" host is for docker deploying via docker compose.
    	*/
        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/pizzaorder");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.project.commercial");
    }
}
