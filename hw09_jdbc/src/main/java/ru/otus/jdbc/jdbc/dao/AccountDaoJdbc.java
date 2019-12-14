package ru.otus.jdbc.jdbc.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.api.dao.AccountDao;
import ru.otus.jdbc.api.dao.UserDaoException;
import ru.otus.jdbc.api.model.Account;
import ru.otus.jdbc.jdbc.DbExecutor;
import ru.otus.jdbc.jdbc.SqlQueryGenerator;
import ru.otus.jdbc.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDaoJdbc implements AccountDao {
    private static Logger logger = LoggerFactory.getLogger(AccountDaoJdbc.class);

    private final SessionManagerJdbc sessionManagerJdbc;
    private final DbExecutor<Account> dbExecutor;

    public AccountDaoJdbc(SessionManagerJdbc sessionManagerJdbc, DbExecutor<Account> dbExecutor) {
        this.sessionManagerJdbc = sessionManagerJdbc;
        this.dbExecutor = dbExecutor;
    }


    @Override
    public Optional<Account> findById(long id) {
        try {
            SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
            sqlQueryGenerator.createSelect(Account.class);
            return dbExecutor.selectRecord(getConnection(), sqlQueryGenerator.getSqlSelect(), id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return new Account( resultSet.getString("type"), resultSet.getInt("rest"));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }


    @Override
    public long saveAccount(Account account) {
        try {
            SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
            sqlQueryGenerator.createInsert(account);
            return dbExecutor.insertRecord(getConnection(), sqlQueryGenerator.getSqlInsert(), sqlQueryGenerator.getOtherfieldsvalues());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void updateAccount(Account account, long no) {
        try {
            SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
            sqlQueryGenerator.createUpdate(account);
            dbExecutor.updateRecord(getConnection(), sqlQueryGenerator.getSqlUpdate(), no, sqlQueryGenerator.getOtherfieldsvalues());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }


    @Override
    public ru.otus.jdbc.api.sessionmanager.SessionManager getSessionManagerJdbc() {
        return sessionManagerJdbc;
    }

    private Connection getConnection() {
        return sessionManagerJdbc.getCurrentSession().getConnection();
    }
}

