package ru.otus.jdbc.api.dao;

import ru.otus.jdbc.api.model.Account;
import ru.otus.jdbc.api.sessionmanager.SessionManager;

import java.util.Optional;

public interface AccountDao {
    Optional<Account> findById(long id);

    long saveAccount(Account account);

    void updateAccount(Account account, long no);

    SessionManager getSessionManagerJdbc();
}
