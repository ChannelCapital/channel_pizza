package edu.sabanciuniv.project.mongoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class MongoBean {
	
private final MongoDatabaseFactory mongo;
	
	@Autowired
	public MongoBean(MongoDatabaseFactory mongodb) {
		this.mongo=mongodb;
	}
}
