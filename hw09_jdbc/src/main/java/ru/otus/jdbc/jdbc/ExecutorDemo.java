package ru.otus.jdbc.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.api.model.Account;
import ru.otus.jdbc.api.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class ExecutorDemo {
  private static final String URL = "jdbc:h2:mem:";
  private static Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

  public static void main(String[] args) throws SQLException {
    ExecutorDemo demo = new ExecutorDemo();

    try (Connection connection = DriverManager.getConnection(URL)) {
      connection.setAutoCommit(false);
      demo.createTable(connection);

      DbExecutor<User> executor = new DbExecutor<>();

      long userId = executor.insertRecord(connection, "insert into user(name) values (?)", Collections.singletonList("testUserName"));
      logger.info("created user:{}", userId);
      connection.commit();
      //System.out.println(executor.getAnnotatedFields(User.class));
      SqlQueryGenerator sql = new SqlQueryGenerator();
      User user1 = new User(1, "Vasya", 5);
      Account account1 = new Account(1,"Hey", 2);
      sql.createSelect(User.class);
      sql.createSelect(Account.class);
      sql.createInsert(user1);
      sql.createInsert(account1);
      sql.createUpdate(user1);
      Optional<User> user = executor.selectRecord(connection, "select id, name, age from user where id  = ?", 2, resultSet -> {
        try {
          if (resultSet.next()) {
            return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"));
          }
        } catch (SQLException e) {
          logger.error(e.getMessage(), e);
        }
        return null;
      });
      System.out.println(user);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }


  private void createTable(Connection connection) throws SQLException {
    try (PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age bigint(20))")) {
      pst.executeUpdate();
    }
  }


}
