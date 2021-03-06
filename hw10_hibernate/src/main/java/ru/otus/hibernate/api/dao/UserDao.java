package ru.otus.hibernate.api.dao;

import ru.otus.hibernate.api.model.User;
import ru.otus.hibernate.api.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  SessionManager getSessionManager();
}
