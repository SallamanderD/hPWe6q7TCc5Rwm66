package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.util.List;

public interface UserService {
    List<User> getAll() throws DatabaseException;
    User getUserById(int id) throws DatabaseException;
    User getUserByLogin(String login) throws DatabaseException;
    User getUserByEmail(String email) throws DatabaseException;
    void saveUser(User user) throws DatabaseException;
    void deleteUserById(long id) throws DatabaseException;
    void changeUserRole(long userId, long roleId) throws DatabaseException;
    void changeUserBanned(long userId, boolean flag) throws DatabaseException;
}
