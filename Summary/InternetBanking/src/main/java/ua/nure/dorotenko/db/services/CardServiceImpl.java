package ua.nure.dorotenko.db.services;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.ConnectionPool;
import ua.nure.dorotenko.db.DaoFactory;
import ua.nure.dorotenko.db.dao.PgCardDAO;
import ua.nure.dorotenko.db.dao.interfaces.AccountDAO;
import ua.nure.dorotenko.db.dao.interfaces.CardDAO;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {
    private static CardServiceImpl instance;
    private static ConnectionPool connectionPool;
    private static CardDAO cardDAO;
    private static final Logger LOG = Logger.getLogger(CardServiceImpl.class);

    private CardServiceImpl(){
        connectionPool = ConnectionPool.getInstance();
        cardDAO = DaoFactory.getCardDAO();
    }

    public synchronized static CardServiceImpl getInstance(){
        if(instance == null){
            instance = new CardServiceImpl();
        }
        return instance;
    }

    @Override
    public void saveCard(Card card) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            cardDAO.createCard(card, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CREATE_CARD, e);
            throw new DatabaseException(Messages.ERR_CREATE_CARD, e);
        }
    }

    @Override
    public Card getCardById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardDAO.getCardById(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_CARD_BY_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_CARD_BY_ID, e);
        }
    }

    @Override
    public void deleteCardById(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            cardDAO.deleteCard(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_DELETE_CARD_BY_ID, e);
            throw new DatabaseException(Messages.ERR_DELETE_CARD_BY_ID, e);
        }
    }

    @Override
    public List<Card> getCardsByUserId(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardDAO.getCardsByUserId(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_CARDS_BY_USER, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_CARDS_BY_USER, e);
        }
    }

    @Override
    public Card getCardByNumber(String number) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardDAO.getCardByNumber(number, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_CARD_BY_NUMBER, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_CARD_BY_NUMBER, e);
        }
    }

    @Override
    public List<Card> getCardsByAccountId(long id) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            return cardDAO.getCardsByAccountId(id, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_OBTAIN_CARDS_BY_ACCOUNT_ID, e);
            throw new DatabaseException(Messages.ERR_OBTAIN_CARDS_BY_ACCOUNT_ID, e);
        }
    }

    @Override
    public void changePINById(long id, String PIN) throws DatabaseException {
        try(Connection connection = connectionPool.getConnection()){
            cardDAO.changePIN(id, PIN, connection);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CHANGE_PIN, e);
            throw new DatabaseException(Messages.ERR_CHANGE_PIN, e);
        }
    }
}
