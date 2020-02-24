package ru.otus.hw13_DI.services;

import ru.otus.hw13_DI.domain.User;

import java.util.ArrayList;

public interface UserService {

    long saveUser(User user);

    boolean authenticateUser(String login, String password);

    ArrayList<User> getAll();
}
