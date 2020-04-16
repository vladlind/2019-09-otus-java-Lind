package ru.otus.hw15_MS.repository;


import ru.otus.hw15_MS.domain.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByLogin(String login);

    ArrayList<User> findAll();

    long saveUser(User user);
}
