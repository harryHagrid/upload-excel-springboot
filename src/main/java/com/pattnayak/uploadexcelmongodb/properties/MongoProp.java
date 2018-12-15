package com.pattnayak.uploadexcelmongodb.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:mongodb.properties")
@ConfigurationProperties(prefix="mongodb")
public class MongoProp {
	
	@Getter
	@Setter 
	private String host;
	@Getter
	@Setter
	private int port;
	@Getter
	@Setter
	private String database;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String password;



}
