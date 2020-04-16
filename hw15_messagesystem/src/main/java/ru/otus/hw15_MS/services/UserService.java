package ru.otus.hw15_MS.services;

import ru.otus.hw15_MS.domain.User;
import java.util.ArrayList;

public interface UserService {

    long saveUser(User user);

    boolean authenticateUser(String login, String password);

    ArrayList<User> getAll();
}
