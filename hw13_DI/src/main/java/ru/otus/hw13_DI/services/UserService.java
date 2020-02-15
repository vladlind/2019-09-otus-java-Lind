package ru.otus.hw13_DI.services;

import ru.otus.hw13_DI.domain.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService {
    long saveUser(User user);

    Optional<User> getUser(String login);

    ArrayList<User> getAll();
}
