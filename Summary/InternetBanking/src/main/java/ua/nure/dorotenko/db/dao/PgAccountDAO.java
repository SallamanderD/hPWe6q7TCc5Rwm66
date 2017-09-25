package ua.nure.dorotenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.interfaces.AccountDAO;
import ua.nure.dorotenko.entities.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgAccountDAO implements AccountDAO {
    private static AccountDAO instance;
    private static final String SELECT_ALL_ACCOUNTS = "SELECT * FROM accounts";
    private static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id=?";
    private static final String CREATE_ACCOUNT = "INSERT INTO accounts VALUES(DEFAULT, 0, ?, ?, ?, FALSE)";
    private static final String CHANGE_BALANCE_BY_ID = "UPDATE accounts SET balance=? WHERE id=?";
    private static final String SELECT_ACCOUNTS_BY_USER_ID = "SELECT * FROM accounts WHERE user_id=?";
    private static final String SELECT_ACC_NUMBERS = "SELECT account_number FROM accounts";
    private static final String CHANGE_BLOCKED_STATUS = "UPDATE accounts SET blocked=? WHERE id=?";
    private static final Logger LOG = Logger.getLogger(PgAccountDAO.class);
    private PgAccountDAO(){

    }

    public synchronized static AccountDAO getInstance() {
        if(instance == null){
            instance = new PgAccountDAO();
            LOG.trace("PgAccountDAO instanced.");
        }
        return instance;
    }

    @Override
    public List<Account> getAllAccounts(Connection connection) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_ACCOUNTS);
        while (resultSet.next()){
            accounts.add(extractAccount(resultSet));
        }
        return accounts;
    }

    @Override
    public Account getAccountById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractAccount(resultSet);
        }
        return null;
    }

    @Override
    public List<Account> getAccountsByUserId(long userId, Connection connection) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNTS_BY_USER_ID);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            accounts.add(extractAccount(resultSet));
        }
        return accounts;
    }

    @Override
    public void createAccount(Account account, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ACCOUNT);
        preparedStatement.setLong(1, account.getUserId());
        preparedStatement.setString(2, account.getNumber());
        preparedStatement.setString(3, account.getName());
        preparedStatement.execute();
    }

    @Override
    public void changeBalanceById(long id, double balance, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_BALANCE_BY_ID);
        preparedStatement.setDouble(1, balance);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
    }

    @Override
    public Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setBalance(resultSet.getDouble("balance"));
        account.setUserId(resultSet.getLong("user_id"));
        account.setNumber(resultSet.getString("account_number"));
        account.setName(resultSet.getString("name"));
        account.setBlocked(resultSet.getBoolean("blocked"));
        return account;
    }

    @Override
    public List<String> getAllAccNumbers(Connection connection) throws SQLException {
        List<String> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ACC_NUMBERS);
        while(resultSet.next()){
            result.add(resultSet.getString(1));
        }
        return result;
    }

    @Override
    public void changeBlockedStatus(long id, Connection connection, boolean flag) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_BLOCKED_STATUS);
        preparedStatement.setBoolean(1, flag);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
    }
}
