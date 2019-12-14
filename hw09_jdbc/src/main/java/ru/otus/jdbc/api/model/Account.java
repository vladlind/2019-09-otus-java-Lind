package ru.otus.jdbc.api.model;

import ru.otus.jdbc.api.dao.Id;


public class Account {
  @Id
  private long no;
  private final String type;
  private final double rest;

  public long getNo() {
    return no;
  }

  public String getType() {
    return type;
  }

  public double getRest() {
    return rest;
  }

  public Account(String type, double rest) {
    this.type = type;
    this.rest = rest;
  }

  public Account(long no, String type, double rest) {
    this.no = no;
    this.type = type;
    this.rest = rest;
  }

  @Override
  public String toString() {
    return "Account{" +
            "no=" + no +
            ", type='" + type + '\'' +", rest="+ rest +
            '}';
  }
}
