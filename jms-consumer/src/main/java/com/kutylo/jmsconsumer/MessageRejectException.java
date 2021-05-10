package com.kutylo.jmsconsumer;

public class MessageRejectException extends RuntimeException{
  public MessageRejectException() {
  }

  public MessageRejectException(String message) {
    super(message);
  }
}
