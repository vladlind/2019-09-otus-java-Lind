package ru.otus.hibernate.api.service;

import ru.otus.hibernate.api.model.User;

import java.util.Optional;

public interface DBServiceUser {

  long saveUser(User user);

  Optional<User> getUser(long id);

}
