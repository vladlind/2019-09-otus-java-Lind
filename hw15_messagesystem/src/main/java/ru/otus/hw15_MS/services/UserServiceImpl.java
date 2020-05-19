package ru.otus.hw15_MS.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw15_MS.domain.User;

import ru.otus.hw15_MS.repository.UserRepository;

import javax.persistence.NoResultException;
import java.util.ArrayList;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public long saveUser(User user) {
        return this.userRepository.saveUser(user);
    }

    @Override
    public boolean authenticateUser(String login, String password) {
        try {
            return this.userRepository.findByLogin(login)
                    .map(user -> user.getPassword().equals(password) && user.isAdmin())
                    .orElse(false);
        } catch (NoResultException nre) {
            return false;
        }
    }

    @Override
    public ArrayList<User> getAll() {
        return this.userRepository.findAll();
    }
}
