package com.kutylo.jmslogger.service;

import com.kutylo.jmslogger.model.OrderDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class LoggerConsumer {
  private static final String DETAILS_QUEUE = "orders-details";
  private static final Logger log = LogManager.getLogger(LoggerConsumer.class.getName());

  @JmsListener(destination = DETAILS_QUEUE)
  public void receiveLiquidOrder(OrderDetail order) {
    log.info("OrderDetail: {}", order);
    System.out.println(order);
  }
}
