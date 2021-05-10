package com.kutylo.jmslogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsLoggerApplication.class, args);
	}

}
