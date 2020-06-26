package com.cts.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.cts.customer.dao")
public class AppConfig {
	
	@Value("${mongo.connection}")
	private String mongoConnection;
	
	@Value("${database.name}")
	private String databaseName;
	
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(mongoConnection);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(),databaseName);
	}
}
