package com.kutylo.jmsproducer.service;

import com.kutylo.jmsproducer.model.Order;
import com.kutylo.jmsproducer.model.OrderType;

import java.util.Scanner;

public class OrderInput {

  private Scanner scanner = new Scanner(System.in);

  public Order inputOrder() {
    Order order = new Order();
    System.out.println("Input user name:");
    order.setUser(scanner.nextLine());
    System.out.println("Input total:");
    order.setTotal(scanner.nextDouble());

    System.out.println("Input type of order:" + "\n" +
        "1-liquid" + "\n" +
        "2-countable");
    if (scanner.nextInt() == 1) {
      order.setOrderType(OrderType.LIQUID);
    } else {
      order.setOrderType(OrderType.COUNTABLE);
    }

    System.out.println("Input amount:");
    order.setAmount(scanner.nextDouble());

    return order;

  }
}
