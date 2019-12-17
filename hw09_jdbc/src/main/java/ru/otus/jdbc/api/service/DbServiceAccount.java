package ru.otus.jdbc.api.service;

import ru.otus.jdbc.api.model.Account;

import java.util.Optional;

public interface DbServiceAccount {

    long saveAccount(Account account);

    void updateAccount(Account account, long no);

    Optional<Account> getAccount(long id);

}
