package ru.otus.jdbc.api.service;

import ru.otus.jdbc.api.model.User;

import java.util.Optional;

public interface DBServiceUser {

  long saveUser(User user);

  Optional<User> getUser(long id);

  void updateUser(User user, long id);

}
