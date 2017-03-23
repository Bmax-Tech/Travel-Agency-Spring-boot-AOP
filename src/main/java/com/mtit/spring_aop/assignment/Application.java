package com.mtit.spring_aop.assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	static final Logger LOGGER = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) {
		LOGGER.info("///////////////////////////////////////////");
		LOGGER.info("////                                   ////");
		LOGGER.info("////       MTIT Assignment - II        ////");
		LOGGER.info("////                                   ////");
		LOGGER.info("////       Spring AOP                  ////");
		LOGGER.info("////                                   ////");
		LOGGER.info("////       Hibernate + Spring Boot     ////");
		LOGGER.info("////                                   ////");
		LOGGER.info("////   -----------------------------   ////");
		LOGGER.info("////                                   ////");
		LOGGER.info("////       @ Buwaneka Boralessa        ////");
		LOGGER.info("////       @ Salika Irushan            ////");
		LOGGER.info("////                                   ////");
		LOGGER.info("///////////////////////////////////////////");
		SpringApplication.run(Application.class, args);
	}
}
