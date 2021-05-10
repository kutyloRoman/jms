package com.kutylo.jmsconsumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderType {

  LIQUID("liquid"), COUNTABLE("countable");

  private final String type;

  private static Map<String, OrderType> FORMAT_MAP = Stream
      .of(OrderType.values())
      .collect(Collectors.toMap(t -> t.type, Function.identity()));

  OrderType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }

//  @JsonCreator // This is the factory method and must be static
//  public static OrderType fromString(String string) {
//    return FORMAT_MAP.get(string);
//        //.orElseThrow(() -> new IlleglArgumentException(string));
//  }
}
