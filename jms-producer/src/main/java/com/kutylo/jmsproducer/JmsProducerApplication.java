package com.kutylo.jmsproducer;

import com.kutylo.jmsproducer.model.Order;
import com.kutylo.jmsproducer.model.OrderType;
import com.kutylo.jmsproducer.service.OrderInput;
import com.kutylo.jmsproducer.service.OrderSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class JmsProducerApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JmsProducerApplication.class, args);

		OrderSender orderSender = new OrderSender(context.getBean(JmsTemplate.class));
		OrderInput orderInput = new OrderInput();
		Order order = orderInput.inputOrder();
		orderSender.send(order);
	}

}
