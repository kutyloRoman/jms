package com.kutylo.jmsproducer.model;

public enum OrderStatus {

  ACCEPTED("accepted"), REJECTED("rejected");

  private final String status;

  OrderStatus(String status) {
    this.status = status;
  }
}
