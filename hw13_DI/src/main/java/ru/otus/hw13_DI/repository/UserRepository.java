package ru.otus.hw13_DI.repository;

import ru.otus.hw13_DI.domain.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByLogin(String login);

    ArrayList<User> findAll();

    long saveUser(User user);
}
