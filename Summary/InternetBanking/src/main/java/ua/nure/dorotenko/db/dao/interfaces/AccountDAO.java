package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    List<Account> getAllAccounts(Connection connection) throws SQLException;
    Account getAccountById(long id, Connection connection) throws SQLException;
    List<Account> getAccountsByUserId(long userId, Connection connection) throws SQLException;
    void createAccount(Account account, Connection connection) throws SQLException;
    void changeBalanceById(long id, double balance, Connection connection) throws SQLException;
    Account extractAccount(ResultSet resultSet) throws SQLException;
    List<String> getAllAccNumbers(Connection connection) throws SQLException;
    void changeBlockedStatus(long id, Connection connection, boolean flag) throws SQLException;

}
