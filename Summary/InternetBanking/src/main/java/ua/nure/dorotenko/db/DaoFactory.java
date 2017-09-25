package ua.nure.dorotenko.db;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.*;
import ua.nure.dorotenko.db.dao.interfaces.*;
import ua.nure.dorotenko.utils.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private static Properties properties;
    private static final Logger LOG = Logger.getLogger(DaoFactory.class);
    private static final String RESOURCE_NAME = "application.properties";
    static {
        properties = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            try(InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_NAME)){
                properties.load(inputStream);
                LOG.info(RESOURCE_NAME + " is loaded.");
            }
        } catch (IOException e) {
            LOG.error(Messages.ERR_OBTAIN_PROPS);
        }
    }

    private DaoFactory(){

    }

    public static AccountDAO getAccountDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgAccountDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgAccountDAO.getInstance();
        }
    }

    public static CardDAO getCardDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgCardDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgCardDAO.getInstance();
        }
    }

    public static CardPaymentDAO getCardPaymentDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgCardPaymentDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgCardPaymentDAO.getInstance();
        }
    }

    public static RoleDAO getRoleDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgRoleDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgRoleDAO.getInstance();
        }
    }

    public static UnblockRequestDAO getUnblockRequestDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgUnblockRequestDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgUnblockRequestDAO.getInstance();
        }
    }

    public static UserDAO getUserDAO(){
        if(properties.getProperty("database").equals("postgres")){
            return PgUserDAO.getInstance();
        } else{
            LOG.warn(Messages.ERR_UNKNOWN_DB_PROPS);
            return PgUserDAO.getInstance();
        }
    }
}
