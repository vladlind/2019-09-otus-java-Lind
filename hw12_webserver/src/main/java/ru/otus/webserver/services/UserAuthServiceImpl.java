package ru.otus.webserver.services;


import ru.otus.webserver.api.dao.UserDao;
import ru.otus.webserver.api.service.DBServiceUser;
import ru.otus.webserver.api.service.DbServiceUserImpl;

public class UserAuthServiceImpl implements UserAuthService {

    private final UserDao userDao;

    public UserAuthServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticate(String login, String password) {
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
        return dbServiceUser.getUser(login)
                .map(user -> user.getPassword().equals(password) && user.isAdmin())
                .orElse(false);
    }

}
