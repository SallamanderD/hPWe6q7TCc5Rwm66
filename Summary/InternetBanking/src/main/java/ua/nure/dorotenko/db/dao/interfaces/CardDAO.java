package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface CardDAO {
    List<Card> getAllCards(Connection connection) throws SQLException;
    Card getCardById(long id, Connection connection) throws SQLException;
    List<Card> getCardsByAccountId(long accountId, Connection connection) throws SQLException;
    Card getCardByNumber(String number, Connection connection) throws SQLException;
    void createCard(Card card, Connection connection) throws SQLException;
    void changePIN(long id, String PIN, Connection connection) throws SQLException;
    void deleteCard(long id, Connection connection) throws SQLException;
    Card extractCard(ResultSet resultSet) throws SQLException;
    List<Card> getCardsByUserId(long id, Connection connection) throws SQLException;
}
