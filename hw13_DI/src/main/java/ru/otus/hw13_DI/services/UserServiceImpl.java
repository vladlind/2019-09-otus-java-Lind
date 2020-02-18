package ru.otus.hw13_DI.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw13_DI.domain.User;
import ru.otus.hw13_DI.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public Optional<User> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public ArrayList<User> getAll() {
        return userRepository.findAll();
    }
}
