package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CardPaymentDAO {
    List<CardPayment> getSentPayments(long cardId, Connection connection) throws SQLException;
    List<CardPayment> getReceivedPayments(long cardId, Connection connection) throws SQLException;
    List<CardPayment> getSentExecutedPayments(long cardId, Connection connection) throws SQLException;
    List<CardPayment> getReceivedExecutedPayments(long cardId, Connection connection) throws SQLException;
    void createCardPayment(CardPayment cardPayment, Connection connection) throws SQLException;
    CardPayment getCardPaymentById(long id, Connection connection) throws SQLException;
    CardPayment extractCardPayment(ResultSet resultSet) throws SQLException;
    List<CardPayment> getPreparePayment(long cardId, Connection connection) throws SQLException;
}
