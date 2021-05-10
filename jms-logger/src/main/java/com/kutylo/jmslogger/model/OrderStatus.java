package com.kutylo.jmslogger.model;

public enum OrderStatus {

  ACCEPTED("accepted"), REJECTED0("rejected");


  private String status;

  OrderStatus(String status) {
    this.status = status;
  }
}
