package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.PgAccountDAO;
import ua.nure.dorotenko.db.dao.interfaces.AccountDAO;
import ua.nure.dorotenko.db.dao.interfaces.CardDAO;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private static AccountServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static AccountDAO accountDAO;
    private static Logger LOG = Logger.getLogger(AccountServiceImpl.class);

    private AccountServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        accountDAO = DaoFactory.getAccountDAO();
    }

    public synchronized static AccountServiceImpl getInstance() {
        if(instance == null){
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Account> getAll() throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return accountDAO.getAllAccounts(connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ALL_ACCOUNTS, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ALL_ACCOUNTS, e);
        }
    }

    @Override
    public Account getAccountById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return accountDAO.getAccountById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ACCOUNT_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ACCOUNT_BY_ID, e);
        }
    }

    @Override
    public void saveAccount(Account account) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            accountDAO.createAccount(account, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CREATE_ACCOUNT, e);
            throw new DatabaseException(Messages.ERR_CREATE_ACCOUNT, e);
        }
    }

    @Override
    public List<Account> getAccountsByUserId(long userId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return accountDAO.getAccountsByUserId(userId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ACCOUNTS_BY_USER_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ACCOUNTS_BY_USER_ID, e);
        }
    }

    @Override
    public List<String> getAccNumbers() throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return accountDAO.getAllAccNumbers(connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ACC_NUMBERS, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ACC_NUMBERS, e);
        }
    }

    @Override
    public void changeAccountBlocked(long id, boolean flag) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            accountDAO.changeBlockedStatus(id, connection, flag);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CHANGE_ACC_BLOCKED, e);
            throw new DatabaseException(Messages.ERR_CHANGE_ACC_BLOCKED, e);
        }
    }

    @Override
    public void changeBalanceById(long id, double balance) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            accountDAO.changeBalanceById(id, balance, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CHANGE_BALANCE, e);
            throw new DatabaseException(Messages.ERR_CHANGE_BALANCE, e);
        }
    }
}
