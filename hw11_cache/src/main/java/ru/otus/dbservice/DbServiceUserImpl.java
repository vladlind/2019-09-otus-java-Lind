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

  private HwCache<Long, User> cache = new MyCache<>();
  private HwListener<Long, User> listenerUser =
          (id, user, action) -> logger.info("Cache - key:{}, value:{}, action: {}", id.toString(), user.getName(), action);

  DbServiceUserImpl(UserDao userDao) {
    this.userDao = userDao;
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
        User cachedUser = cache.get(id);
        if (cachedUser == null) {
          cache.addListener(listenerUser);
          Optional<User> userOptional = userDao.findById(id);
          userOptional.ifPresent(user -> cache.put(id, user));
          cache.removeListener(listenerUser);
          logger.info("user: {}", userOptional.orElse(null));
          return userOptional;
        } else {
          logger.info("user from cache: {}", cachedUser.getName());
          return Optional.of(cachedUser);
        }
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
        cache.addListener(listenerUser);
        userDao.updateUser(user, id);
        sessionManager.commitSession();

        logger.info("updated user: {}", id);
        User cachedUser = cache.get(id);
        if (cachedUser != null) {
          cache.remove(id);
        }
        cache.put(id, user);
        cache.removeListener(listenerUser);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }

}
