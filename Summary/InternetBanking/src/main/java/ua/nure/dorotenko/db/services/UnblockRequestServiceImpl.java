package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.interfaces.AccountDAO;
import ua.nure.dorotenko.db.dao.interfaces.CardDAO;
import ua.nure.dorotenko.db.dao.interfaces.UnblockRequestDAO;
import ua.nure.dorotenko.db.services.interfaces.UnblockRequestService;
import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UnblockRequestServiceImpl implements UnblockRequestService {

    private static UnblockRequestServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static UnblockRequestDAO unblockRequestDAO;
    private static AccountDAO accountDAO;
    private static final Logger LOG = Logger.getLogger(UnblockRequestServiceImpl.class);

    private UnblockRequestServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        unblockRequestDAO = DaoFactory.getUnblockRequestDAO();
        accountDAO = DaoFactory.getAccountDAO();
    }

    @Override
    public void saveUnblockRequest(UnblockRequest unblockRequest) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            unblockRequestDAO.saveUnblockRequest(unblockRequest, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_SAVE_REQUEST, e);
            throw new DatabaseException(Messages.ERR_SAVE_REQUEST, e);
        }
    }

    public static UnblockRequestServiceImpl getInstance() {
        if(instance == null){
            instance = new UnblockRequestServiceImpl();
        }
        return instance;
    }

    @Override
    public List<UnblockRequest> getAllUnsatisfiedUnblockRequest() throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return unblockRequestDAO.getAllReqestsUnsatisfied(connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_REQUEST_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_REQUEST_BY_ID, e);
        }
    }

    @Override
    public void changeUnblockRequestStatusToSatisfied(long id) throws DatabaseException {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            unblockRequestDAO.changeRequestStatusById(true, id, connection);
            long accountId = unblockRequestDAO.getRequestById(id, connection).getAccountId();
            accountDAO.changeBlockedStatus(accountId, connection, false);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connectionPool.rollbackConnection(connection);
            LOG.error(Messages.ERR_CHANGE_REQUEST_STATUS, e);
            throw new DatabaseException(Messages.ERR_CHANGE_REQUEST_STATUS, e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}
