package ru.otus.hw13_DI.services;

import ru.otus.hw13_DI.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long saveUser(User user);

    Optional<User> getUser(String login);

    List<User> getAll();
}
