package ru.otus.jdbc.api.dao;

import ru.otus.jdbc.api.model.User;
import ru.otus.jdbc.api.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long saveUser(User user);

    void updateUser(User user, long id);

    SessionManager getSessionManagerJdbc();
}
