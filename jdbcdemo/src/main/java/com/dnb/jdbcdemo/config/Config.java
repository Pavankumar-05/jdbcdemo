package com.dnb.jdbcdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.dnb.jdbcdemo")// for scanning all the subpackages
@PropertySource("application.properties")

public class Config {
	@Autowired
	private Environment environment;
	
}
