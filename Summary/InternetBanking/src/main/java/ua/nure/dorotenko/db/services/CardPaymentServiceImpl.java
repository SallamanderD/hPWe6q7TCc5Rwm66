package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.interfaces.AccountDAO;
import ua.nure.dorotenko.db.dao.interfaces.CardDAO;
import ua.nure.dorotenko.db.dao.interfaces.CardPaymentDAO;
import ua.nure.dorotenko.db.services.interfaces.CardPaymentService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CardPaymentServiceImpl implements CardPaymentService {
    private static CardPaymentServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static CardPaymentDAO cardPaymentDAO;
    private static AccountDAO accountDAO;
    private static CardDAO cardDAO;
    private static final Logger LOG = Logger.getLogger(CardPaymentServiceImpl.class);

    private CardPaymentServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        cardPaymentDAO = DaoFactory.getCardPaymentDAO();
        accountDAO = DaoFactory.getAccountDAO();
        cardDAO = DaoFactory.getCardDAO();
    }

    public synchronized static CardPaymentServiceImpl getInstance(){
        if(instance == null){
            instance = new CardPaymentServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CardPayment> getSentPayments(long cardId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getSentPayments(cardId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_SENDED_PAYMENT, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_SENDED_PAYMENT, e);
        }
    }

    @Override
    public List<CardPayment> getReceivedPayments(long cardId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getReceivedPayments(cardId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_RECEIVED_PAYMENT, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_RECEIVED_PAYMENT, e);
        }
    }

    @Override
    public List<CardPayment> getExecutedSentPayments(long cardId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getSentExecutedPayments(cardId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_SENDED_PAYMENT, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_SENDED_PAYMENT, e);
        }
    }

    @Override
    public List<CardPayment> getPreparePayment(long cardId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getPreparePayment(cardId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_PREPARE_PAYMENTS, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_PREPARE_PAYMENTS, e);
        }
    }

    @Override
    public List<CardPayment> getExecutedReceivedPayments(long cardId) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getReceivedExecutedPayments(cardId, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_RECEIVED_PAYMENT, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_RECEIVED_PAYMENT, e);
        }
    }

    @Override
    public boolean saveCardPayment(CardPayment cardPayment) throws DatabaseException {
        Connection connection = null;
        Account accountFrom;
        Account accountTo;
        try{
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            accountFrom = accountDAO.getAccountById(cardDAO.getCardById(cardPayment.getSenderCardId(), connection).getAccountId(), connection);
            accountTo = accountDAO.getAccountById(cardDAO.getCardById(cardPayment.getReceiverCardId(), connection).getAccountId(), connection);
            if(!cardPayment.isPrepared()){
                accountFrom.setBalance(accountFrom.getBalance() - cardPayment.getSum());
                accountTo.setBalance(accountTo.getBalance() + cardPayment.getSum());
                accountDAO.changeBalanceById(accountFrom.getId(), accountFrom.getBalance(), connection);
                accountDAO.changeBalanceById(accountTo.getId(), accountTo.getBalance(), connection);
            }
            cardPayment.setDatetime(new Date());
            cardPaymentDAO.createCardPayment(cardPayment, connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e){
            connectionPool.rollbackConnection(connection);
            LOG.error(Messages.ERR_CREATE_PAYMENT, e);
            throw new DatabaseException(Messages.ERR_CREATE_PAYMENT, e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return true;
    }

    @Override
    public CardPayment getCardPaymentById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardPaymentDAO.getCardPaymentById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_PAYMENT_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_PAYMENT_BY_ID, e);
        }
    }
}
