package ua.nure.dorotenko.db;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static DataSource dataSource;
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() throws DatabaseException {
        try{
            InitialContext initContext = new InitialContext();
            Context envCtx = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/InternetBankingDB");
            LOG.trace("DataSource --> " + dataSource);
        } catch (NamingException e) {
            LOG.error(Messages.ERR_OBTAIN_DS, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_DS, e);
        }

    }

    public synchronized static ConnectionPool getInstance() {
        if(instance == null){
            try{
                instance = new ConnectionPool();
            } catch (DatabaseException e){
                LOG.error("Error while establishing data source.");
            }
        }
        return instance;
    }

    public Connection getConnection() throws DatabaseException {
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_CONNECTION, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_CONNECTION, e);
        }
    }

    public void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(e.getClass()).error("Error while connection closing.");
            }
        }
    }

    public void rollbackConnection(Connection connection){
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(e.getClass()).error("Error while connection rollbacking.");
            }
        }
    }
}
