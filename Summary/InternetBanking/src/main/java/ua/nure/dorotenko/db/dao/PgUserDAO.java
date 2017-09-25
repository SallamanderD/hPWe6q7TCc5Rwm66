package ua.nure.dorotenko.db.dao;

import ua.nure.dorotenko.db.dao.interfaces.UserDAO;
import ua.nure.dorotenko.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgUserDAO implements UserDAO {
    private static PgUserDAO instance;
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, FALSE)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String UPDATE_USER_ROLE = "UPDATE users SET role_id=? WHERE id=?";
    private static final String UPDATE_USER_BANNED = "UPDATE users SET banned=? WHERE id=?";
    private PgUserDAO(){

    }

    @Override
    public void changeUserRole(long id, long roleId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE);
        preparedStatement.setLong(1, roleId);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
    }

    public synchronized static PgUserDAO getInstance(){
        if(instance == null){
            instance = new PgUserDAO();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
        while(resultSet.next()){
            users.add(extractUser(resultSet));
        }
        return users;
    }

    @Override
    public User getUserById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractUser(resultSet);
        }
        return null;
    }

    @Override
    public void deleteUserById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }

    @Override
    public void createUser(User entity, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, entity.getFullName());
        preparedStatement.setString(2, entity.getLogin());
        preparedStatement.setString(3, entity.getPassword());
        preparedStatement.setString(4, entity.getTelephone());
        preparedStatement.setLong(5, entity.getRoleId());
        preparedStatement.setString(6, entity.getEmail());
        preparedStatement.execute();
    }

    public User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setTelephone(resultSet.getString("telephone"));
        user.setRoleId(resultSet.getLong("role_id"));
        user.setEmail(resultSet.getString("email"));
        user.setBanned(resultSet.getBoolean("banned"));
        return user;
    }

    @Override
    public User getUserByLogin(String login, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractUser(resultSet);
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractUser(resultSet);
        }
        return null;
    }

    @Override
    public void changeUserBanned(long userId, boolean flag, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BANNED);
        preparedStatement.setBoolean(1, flag);
        preparedStatement.setLong(2, userId);
        preparedStatement.execute();
    }
}
