package ua.nure.dorotenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.interfaces.CardDAO;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgCardDAO implements CardDAO {
    private static PgCardDAO instance;
    private static final String SELECT_ALL_CARDS = "SELECT * FROM cards";
    private static final String SELECT_CARD_BY_ID = "SELECT * FROM cards WHERE id=?";
    private static final String SELECT_CARDS_BY_ACCOUNT_ID = "SELECT * FROM cards WHERE account_id=?";
    private static final String INSERT_CARD = "INSERT INTO cards VALUES(DEFAULT, ?, ?, ?)";
    private static final String CHANGE_PIN_BY_ID = "UPDATE cards SET pin=? WHERE id=?";
    private static final String DELETE_CARD_BY_ID = "DELETE FROM cards WHERE id=?";
    private static final String SELECT_CARD_BY_NUMBER = "SELECT * FROM cards WHERE number=?";
    private static final String SELECT_CARDS_BY_USER_ID = "SELECT cards.* FROM cards INNER JOIN accounts ON cards.account_id = accounts.id WHERE accounts.user_id = ?";
    private static final Logger LOG = Logger.getLogger(PgCardDAO.class);
    private PgCardDAO(){

    }

    public synchronized static PgCardDAO getInstance() {
        if(instance == null){
            instance = new PgCardDAO();
        }
        return instance;
    }

    @Override
    public List<Card> getAllCards(Connection connection) throws SQLException {
        List<Card> cards = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_CARDS);
        while(resultSet.next()){
            cards.add(extractCard(resultSet));
        }
        return cards;
    }

    @Override
    public Card getCardById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARD_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractCard(resultSet);
        }
        return null;
    }

    @Override
    public List<Card> getCardsByAccountId(long accountId, Connection connection) throws SQLException {
        List<Card> cards = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARDS_BY_ACCOUNT_ID);
        preparedStatement.setLong(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            cards.add(extractCard(resultSet));
        }
        return cards;
    }

    @Override
    public void createCard(Card card, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD);
        preparedStatement.setString(1, card.getNumber());
        preparedStatement.setLong(2, card.getAccountId());
        preparedStatement.setString(3, card.getPIN());
        preparedStatement.execute();
    }

    @Override
    public void deleteCard(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD_BY_ID);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }

    @Override
    public Card extractCard(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("id"));
        card.setNumber(resultSet.getString("number"));
        card.setAccountId(resultSet.getLong("account_id"));
        card.setPIN(resultSet.getString("pin"));
        return card;
    }

    @Override
    public Card getCardByNumber(String number, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARD_BY_NUMBER);
        preparedStatement.setString(1, number);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractCard(resultSet);
        }
        return null;
    }

    @Override
    public List<Card> getCardsByUserId(long id, Connection connection) throws SQLException {
        List<Card> cards = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARDS_BY_USER_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cards.add(extractCard(resultSet));
        }
        return cards;
    }

    @Override
    public void changePIN(long id, String PIN, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PIN_BY_ID);
        preparedStatement.setString(1, PIN);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
    }
}
