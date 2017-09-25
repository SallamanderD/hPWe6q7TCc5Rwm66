package ua.nure.dorotenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.interfaces.RoleDAO;
import ua.nure.dorotenko.entities.Role;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgRoleDAO implements RoleDAO {
    private static final String SELECT_ALL_ROLES = "SELECT * FROM roles";
    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE id=?";
    private static final Logger LOG = Logger.getLogger(PgRoleDAO.class);

    private static PgRoleDAO instance;

    private PgRoleDAO(){

    }

    public synchronized static PgRoleDAO getInstance() {
        if(instance == null){
            instance = new PgRoleDAO();
        }
        return instance;
    }

    @Override
    public List<Role> getAllRoles(Connection connection) throws SQLException {
        List<Role> roles = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROLES);
        while(resultSet.next()){
            roles.add(extractRole(resultSet));
        }
        return roles;
    }

    @Override
    public Role getRoleById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractRole(resultSet);
        }
        return null;
    }

    @Override
    public Role extractRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));
        return role;
    }
}
