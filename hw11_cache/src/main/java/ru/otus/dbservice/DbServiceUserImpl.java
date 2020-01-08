package ru.otus.dbservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cache.HwCache;
import ru.otus.cache.HwListener;
import ru.otus.cache.MyCache;
import ru.otus.jdbc.api.dao.UserDao;
import ru.otus.jdbc.api.model.User;
import ru.otus.jdbc.api.service.DBServiceUser;
import ru.otus.jdbc.api.service.DbServiceException;
import ru.otus.jdbc.api.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceUserImpl implements DBServiceUser {
  private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

  private final UserDao userDao;

  private final HwCache<Long, User> userCache;

  DbServiceUserImpl(UserDao userDao, HwCache<Long, User> userCache) {
    this.userDao = userDao;
    this.userCache = userCache;
  }

  @Override
  public long saveUser(User user) {
    try (SessionManager sessionManager = userDao.getSessionManagerJdbc()) {
      sessionManager.beginSession();
      try {
        long userId = userDao.saveUser(user);
        sessionManager.commitSession();

        logger.info("created user: {}", userId);
        return userId;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }


  @Override
  public Optional<User> getUser(long id) {
    try (SessionManager sessionManager = userDao.getSessionManagerJdbc()) {
      sessionManager.beginSession();
      try {
        return Optional.ofNullable(userCache.get(id)).or(() -> userDao.findById(id).map(u -> {
          logger.info("user: {}", u);
          userCache.put(u.getId(), u);
          return u;
        }));
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
      }
      return Optional.empty();
    }
  }

  @Override
  public void updateUser(User user, long id) {
    try (SessionManager sessionManager = userDao.getSessionManagerJdbc()) {
      sessionManager.beginSession();
      try {
        userDao.updateUser(user, id);
        sessionManager.commitSession();

        logger.info("updated user: {}", id);
        Optional.ofNullable(userCache.get(id)).ifPresent(u -> userCache.put(u.getId(), u));

      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }

}
