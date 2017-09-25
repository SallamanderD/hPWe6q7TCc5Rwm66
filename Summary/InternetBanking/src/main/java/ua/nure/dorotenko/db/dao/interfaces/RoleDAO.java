package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.Role;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    Role getRoleById(long id, Connection connection) throws SQLException;
    List<Role> getAllRoles(Connection connection) throws SQLException;
    Role extractRole(ResultSet resultSet) throws SQLException;
}
