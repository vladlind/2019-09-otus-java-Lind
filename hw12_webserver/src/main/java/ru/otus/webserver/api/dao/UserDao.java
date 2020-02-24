package ru.otus.webserver.api.dao;


import ru.otus.webserver.api.model.User;
import ru.otus.webserver.api.sessionmanager.SessionManager;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDao {

  Optional<User>  findByLogin(String login);

  ArrayList<User> findAll();

  long saveUser(User user);

  SessionManager getSessionManager();
}