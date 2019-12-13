package ru.otus.jdbc.jdbc.sessionmanager;

import ru.otus.jdbc.api.sessionmanager.DatabaseSession;

import java.sql.Connection;

public class DatabaseSessionJdbc implements ru.otus.jdbc.api.sessionmanager.DatabaseSession {
  private final Connection connection;

  DatabaseSessionJdbc(Connection connection) {
    this.connection = connection;
  }

  public Connection getConnection() {
    return connection;
  }
}
