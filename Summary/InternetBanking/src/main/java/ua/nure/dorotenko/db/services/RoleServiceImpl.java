package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.PgRoleDAO;
import ua.nure.dorotenko.db.dao.interfaces.RoleDAO;
import ua.nure.dorotenko.db.services.interfaces.RoleService;
import ua.nure.dorotenko.entities.Role;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleServiceImpl implements RoleService {
    private static RoleServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static RoleDAO roleDAO;
    private static final Logger LOG = Logger.getLogger(RoleServiceImpl.class);
    private  RoleServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        roleDAO = DaoFactory.getRoleDAO();
    }

    public static RoleServiceImpl getInstance() {
        if(instance == null){
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    @Override
    public Role getRoleById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return roleDAO.getRoleById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_ROLE_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_ROLE_BY_ID, e);
        }
    }
}
