package com.kutylo.jmslogger.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class OrderDetail {
  private Order order;
  private OrderStatus orderStatus;

  public OrderDetail() {
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  @Override
  public String toString() {
    return "OrderDetail{" +
        "order=" + order +
        ", orderStatus=" + orderStatus +
        '}';
  }
}
