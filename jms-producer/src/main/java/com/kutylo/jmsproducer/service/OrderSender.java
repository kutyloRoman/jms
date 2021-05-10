package com.kutylo.jmsproducer.service;

import com.kutylo.jmsproducer.exception.MessageRejectException;
import com.kutylo.jmsproducer.model.Order;
import com.kutylo.jmsproducer.model.OrderDetail;
import com.kutylo.jmsproducer.model.OrderStatus;
import com.kutylo.jmsproducer.model.OrderType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Component

public class OrderSender {

  private JmsTemplate jmsTemplate;
  private static final String QUEUE = "orders";
  private static final String DETAILS_QUEUE = "orders-details";

  private double maxLitres = 50.0;
  private int maxTotal = 125;

  public OrderSender(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void send(Order order) {
    checkOrder(order);

    if (order.getOrderType().equals(OrderType.LIQUID)) {
      jmsTemplate.convertAndSend(QUEUE, order, setSelectorProperty("liquid"));
    } else {
      jmsTemplate.convertAndSend(QUEUE, order, setSelectorProperty("countable"));
    }
  }

  private MessagePostProcessor setSelectorProperty(String property) {
    return messagePostProcessor -> {
      messagePostProcessor.setStringProperty("orderType",
          property);
      return messagePostProcessor;
    };
  }

  private void checkOrder(Order order) {
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setOrder(order);

    if (order.getTotal() > maxTotal ||
        (order.getOrderType().equals(OrderType.LIQUID) && order.getAmount() > maxLitres)) {
      orderDetail.setOrderStatus(OrderStatus.REJECTED);
      sendOrderDetails(orderDetail);
      throw new MessageRejectException("Not allowed parameter size");
    }

    orderDetail.setOrderStatus(OrderStatus.ACCEPTED);
    sendOrderDetails(orderDetail);

  }

  private void sendOrderDetails(OrderDetail orderDetail) {
    jmsTemplate.convertAndSend(DETAILS_QUEUE, orderDetail);
  }
}
