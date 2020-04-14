package ru.otus.hw15_MS.messagesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public enum MessageType {
  USER_DATA("UserData");

  @Value("UserData")
  private final String value;

  @Autowired
  MessageType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
