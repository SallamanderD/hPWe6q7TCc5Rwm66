package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.PgUserDAO;
import ua.nure.dorotenko.db.dao.interfaces.UserDAO;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static UserDAO userDAO;
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private UserServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        userDAO = DaoFactory.getUserDAO();
    }

    public synchronized static UserServiceImpl getInstance() {
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAll() throws DatabaseException {
        List<User> users;
        try(Connection connection = connectionPool.getConnection()){
            users = userDAO.getAllUsers(connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ALL_USERS, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ALL_USERS, e);
        }
        return users;
    }

    @Override
    public User getUserById(int id) throws DatabaseException {
        User user;
        try(Connection connection = connectionPool.getConnection()){
            user = userDAO.getUserById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_USER_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_USER_BY_ID, e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DatabaseException {
        User user;
        try(Connection connection = connectionPool.getConnection()){
            user = userDAO.getUserByEmail(email, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_USER_BY_EMAIL);
            throw new DatabaseException(Messages.ERR_OBTAIN_USER_BY_EMAIL, e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DatabaseException {
        User user;
        try(Connection connection = connectionPool.getConnection()){
            user = userDAO.getUserByLogin(login, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_USER_BY_LOGIN, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_USER_BY_LOGIN, e);
        }
        return user;
    }

    @Override
    public void saveUser(User user) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            userDAO.createUser(user, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CREATE_USER, e);
            throw new DatabaseException(Messages.ERR_CREATE_USER, e);
        }
    }

    @Override
    public void deleteUserById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            userDAO.deleteUserById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_DELETE_USER_BY_ID, e);
            throw new DatabaseException(Messages.ERR_DELETE_USER_BY_ID, e);
        }
    }

    @Override
    public void changeUserBanned(long userId, boolean flag) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            userDAO.changeUserBanned(userId,flag, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CHANGE_USER_BANNED, e);
            throw new DatabaseException(Messages.ERR_CHANGE_USER_BANNED, e);
        }
    }

    @Override
    public void changeUserRole(long userId, long roleId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            userDAO.changeUserRole(userId, roleId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CHANGE_USER_ROLE, e);
            throw new DatabaseException(Messages.ERR_CHANGE_USER_ROLE, e);
        }
    }


}
