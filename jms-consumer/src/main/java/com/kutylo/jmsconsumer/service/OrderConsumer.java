package com.kutylo.jmsconsumer.service;

import com.kutylo.jmsconsumer.model.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
  private static final String QUEUE = "orders";

  @JmsListener(destination = QUEUE, selector = "orderType = 'liquid'")
  public void receiveLiquidOrder(Order order){

    System.out.println("Liquid order: " + order);
  }

  @JmsListener(destination = QUEUE, selector = "orderType = 'countable'")
  public void receiveCountableOrder(Order order){

    System.out.println("Count order: " + order);
  }

}
