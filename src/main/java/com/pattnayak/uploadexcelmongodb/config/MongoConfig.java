package com.pattnayak.uploadexcelmongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.pattnayak.uploadexcelmongodb.properties.MongoProp;

import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
public class MongoConfig 
//extends AbstractMongoConfiguration 
{

//	@Autowired
//	private MongoProp mongoProp;
//
//	@Override
//	public MongoClient mongoClient() {
//		log.info("configuring the mongo DB properties");
//		log.info("******************************");
//		log.info("mongo db properties---");
//		log.info("hostname - "+this.mongoProp.getHost());
//
//		return new MongoClient(new MongoClientURI(this.getRemoteMongoURI()));
//	}
//
//	@Override
//	protected String getDatabaseName() {
//		log.info("");
//		log.info("mongo database name - " + this.mongoProp.getDatabase());
//		return this.mongoProp.getDatabase();
//	}
//
//	private String getRemoteMongoURI() {
//
//		String uri = "mongodb://" + this.mongoProp.getUsername() + ":" + this.mongoProp.getPassword() + "@" + this.mongoProp.getHost() + ":"
//				+ this.mongoProp.getPort() + "/" + this.mongoProp.getDatabase();
//
//		log.info("MongoClient URI - " + uri + "\n");
//		return uri;
//	}

}
