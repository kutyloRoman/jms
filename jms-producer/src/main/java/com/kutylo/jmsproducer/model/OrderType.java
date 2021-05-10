package com.kutylo.jmsproducer.model;

public enum OrderType {

  LIQUID("liquid"), COUNTABLE("countable");

  private final String type;

  OrderType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

}
