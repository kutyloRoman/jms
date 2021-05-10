package com.kutylo.jmslogger.model;

public enum OrderType {

  LIQUID("liquid"), COUNTABLE("countable");


  private String type;

  OrderType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }


}
