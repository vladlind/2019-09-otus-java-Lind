package ru.otus.jdbc.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.api.dao.AccountDao;
import ru.otus.jdbc.api.dao.UserDao;
import ru.otus.jdbc.api.model.Account;
import ru.otus.jdbc.api.model.User;
import ru.otus.jdbc.api.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceAccountImpl implements DbServiceAccount {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final AccountDao accountDao;

    public DbServiceAccountImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public long saveAccount(Account account) {
        try (SessionManager sessionManager = accountDao.getSessionManagerJdbc()) {
            sessionManager.beginSession();
            try {
                long accountNo = accountDao.saveAccount(account);
                sessionManager.commitSession();

                logger.info("created account: {}", accountNo );
                return accountNo;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }


    @Override
    public Optional<Account> getAccount(long id) {
        try (SessionManager sessionManager = accountDao.getSessionManagerJdbc()) {
            sessionManager.beginSession();
            try {
                Optional<Account> accountOptional = accountDao.findById(id);

                logger.info("account: {}", accountOptional.orElse(null));
                return accountOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public void updateAccount(Account account, long no) {
        try (SessionManager sessionManager = accountDao.getSessionManagerJdbc()) {
            sessionManager.beginSession();
            try {
                accountDao.updateAccount(account, no);
                sessionManager.commitSession();

                logger.info("updated account: {}", no);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }
}
