package ru.otus.dbservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cache.HwCache;
import ru.otus.cache.HwListener;
import ru.otus.cache.MyCache;
import ru.otus.jdbc.api.dao.UserDao;
import ru.otus.jdbc.api.model.User;
import ru.otus.jdbc.api.service.DBServiceUser;
import ru.otus.jdbc.h2.DataSourceH2;
import ru.otus.jdbc.jdbc.DbExecutor;
import ru.otus.jdbc.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class DbServiceDemoWithCache {

  private static Logger logger = LoggerFactory.getLogger(DbServiceDemoWithCache.class);

  public static void main(String[] args) throws Exception {
    DataSource dataSource = new DataSourceH2();
    DbServiceDemoWithCache demo = new DbServiceDemoWithCache();

    demo.createTableUser(dataSource);

    HwCache<Long, User> userCache = new MyCache<>();
    HwListener<Long, User> listenerUser =
            (id, user, action) -> logger.info("Listener Cache - key:{}, value:{}, action: {}", id.toString(), user.getName(), action);
    HwListener<Long, User> listenerUser2 =
            (id, user, action) -> logger.info("Listener2 Cache - key:{}, value:{}, action: {}", id.toString(), user.getAge(), action);
    userCache.addListener(listenerUser);
    userCache.addListener(listenerUser2);

    SessionManagerJdbc sessionManagerJdbc = new SessionManagerJdbc(dataSource);

    DbExecutor<User> dbExecutor = new DbExecutor<>();
    UserDao userDao = new UserDaoJdbc(sessionManagerJdbc, dbExecutor);
    DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao, userCache);

    long id = dbServiceUser.saveUser(new User("dbServiceUser", 8));
    Optional<User> user1 = dbServiceUser.getUser(id);
    System.out.println("----------");
    Optional<User> user1a = dbServiceUser.getUser(id);
    System.out.println("----------");
    long id2 = dbServiceUser.saveUser(new User("dbServiceUser2", 9));
    Optional<User> user2 = dbServiceUser.getUser(id2);
    System.out.println("----------");
    dbServiceUser.updateUser(new User( "dbServiceUser3", 18), id2);
    System.out.println("----------");
    Optional<User> user3 = dbServiceUser.getUser(id2);

    userCache.removeListener(listenerUser);
    userCache.removeListener(listenerUser2);
  }

  private void createTableUser(DataSource dataSource) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement pst = connection.prepareStatement("create table user(id long NOT NULL auto_increment, name varchar(255), age int(3))")) {
      pst.executeUpdate();
    }
    System.out.println("table created");
  }
}
