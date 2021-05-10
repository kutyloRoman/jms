package com.kutylo.jmslogger.model;

import java.io.Serializable;

public class Order implements Serializable {
  private String user;
  private OrderType orderType;
  private double amount;
  private double total;

  public Order() {
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Order{" +
        "user='" + user + '\'' +
        ", orderType=" + orderType +
        ", amount=" + amount +
        ", total=" + total +
        '}';
  }
}
