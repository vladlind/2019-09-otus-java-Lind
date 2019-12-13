package ru.otus.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.api.dao.AccountDao;
import ru.otus.jdbc.api.dao.UserDao;
import ru.otus.jdbc.api.model.Account;
import ru.otus.jdbc.api.model.User;
import ru.otus.jdbc.api.service.DBServiceUser;
import ru.otus.jdbc.api.service.DbServiceAccount;
import ru.otus.jdbc.api.service.DbServiceAccountImpl;
import ru.otus.jdbc.api.service.DbServiceUserImpl;
import ru.otus.jdbc.h2.DataSourceH2;
import ru.otus.jdbc.jdbc.DbExecutor;
import ru.otus.jdbc.jdbc.dao.AccountDaoJdbc;
import ru.otus.jdbc.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

  public static void main(String[] args) throws Exception {
    DataSource dataSource = new DataSourceH2();
    DbServiceDemo demo = new DbServiceDemo();

    demo.createTableUser(dataSource);
    demo.createTableAccount(dataSource);

    SessionManagerJdbc sessionManagerJdbc = new SessionManagerJdbc(dataSource);

    DbExecutor<User> dbExecutor = new DbExecutor<>();
    UserDao userDao = new UserDaoJdbc(sessionManagerJdbc, dbExecutor);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

    long id = dbServiceUser.saveUser(new User(0, "dbServiceUser", 8));
    Optional<User> user1 = dbServiceUser.getUser(id);
    long id2 = dbServiceUser.saveUser(new User(0, "dbServiceUser2", 9));
    Optional<User> user2 = dbServiceUser.getUser(5);
    dbServiceUser.updateUser(new User(0, "dbServiceUser3", 18), id2);
    Optional<User> user3 = dbServiceUser.getUser(id2);

    System.out.println("----------------");

    DbExecutor<Account> dbExecutor2 = new DbExecutor<>();
    AccountDao accountDao = new AccountDaoJdbc(sessionManagerJdbc, dbExecutor2);
    DbServiceAccount dbServiceAccount =  new DbServiceAccountImpl(accountDao);

    long no = dbServiceAccount.saveAccount(new Account(0, "accounttype", 10 ));
    Optional<Account> account = dbServiceAccount.getAccount(no);
    dbServiceAccount.updateAccount(new Account(0,"accounttype2", 100), no);
    Optional<Account> account2 = dbServiceAccount.getAccount(no);

//    System.out.println(user);
//    user.ifPresentOrElse(
//        crUser -> logger.info("created user, name:{}", crUser.getName()),
//        () -> logger.info("user was not created")
//    );
//
//    System.out.println(account);
//    account.ifPresentOrElse(
//            crAcc -> logger.info("created account, name:{}", crAcc.getType()),
//            () -> logger.info("account was not created")
//    );

  }

  private void createTableUser(DataSource dataSource) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement pst = connection.prepareStatement("create table user(id long NOT NULL auto_increment, name varchar(255), age int(3))")) {
      pst.executeUpdate();
    }
    System.out.println("table created");
  }

  private void createTableAccount(DataSource dataSource) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement pst = connection.prepareStatement("create table account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)")) {
      pst.executeUpdate();
    }
    System.out.println("table created");
  }
}
