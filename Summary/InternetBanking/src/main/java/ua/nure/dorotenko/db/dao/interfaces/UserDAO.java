package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers(Connection connection) throws SQLException;
    User getUserById(long id, Connection connection) throws SQLException;
    User getUserByLogin(String login, Connection connection) throws SQLException;
    User getUserByEmail(String email, Connection connection) throws SQLException;
    void deleteUserById(long id, Connection connection) throws SQLException;
    void createUser(User user, Connection connection) throws SQLException;
    User extractUser(ResultSet resultSet) throws SQLException;
    void changeUserRole(long id, long roleId, Connection connection) throws SQLException;
    void changeUserBanned(long userId, boolean flag, Connection connection) throws SQLException;
}
