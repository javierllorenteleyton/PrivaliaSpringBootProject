package com.privalia;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.privalia.bootstrap.SpringJpaBootstrap;

@SpringBootApplication
public class PrivaliaSpringBootProjectApplication {

	private Logger log = LoggerFactory.getLogger(PrivaliaSpringBootProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PrivaliaSpringBootProjectApplication.class, args);
		
	}
	

}
