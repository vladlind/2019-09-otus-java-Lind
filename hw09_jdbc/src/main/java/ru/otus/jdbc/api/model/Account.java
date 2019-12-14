package ru.otus.jdbc.api.model;

import ru.otus.jdbc.api.dao.Id;


public class Account {
  @Id
  private final long no = 0;
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


  @Override
  public String toString() {
    return "Account{" +
            "no=" + no +
            ", type='" + type + '\'' +", rest="+ rest +
            '}';
  }
}
