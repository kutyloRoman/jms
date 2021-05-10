package com.kutylo.jmsproducer.exception;

public class MessageRejectException extends RuntimeException {
  public MessageRejectException() {
  }

  public MessageRejectException(String message) {
    super(message);
  }
}
