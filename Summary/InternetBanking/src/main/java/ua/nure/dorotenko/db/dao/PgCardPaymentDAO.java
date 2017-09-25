package ua.nure.dorotenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.interfaces.CardPaymentDAO;
import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgCardPaymentDAO implements CardPaymentDAO {
    private static PgCardPaymentDAO instance;
    private static final String SELECT_ALL_PAYMENT_BY_SENDER_CARD_ID = "SELECT * FROM card_payments WHERE from_card_id=?";
    private static final String SELECT_ALL_EXECUTED_PAYMENT_BY_SENDER_CARD_ID = "SELECT * FROM card_payments WHERE from_card_id=? AND prepared=FALSE";
    private static final String SELECT_ALL_EXECUTED_PAYMENT_BY_RECEIVER_CARD_ID = "SELECT * FROM card_payments WHERE to_card_id=? AND prepared=FALSE";
    private static final String SELECT_ALL_PAYMENTS_BY_RECEIVER_CARD_ID = "SELECT * FROM card_payments WHERE to_card_id=?";
    private static final String INSERT_PAYMENT = "INSERT INTO card_payments VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SELECT_PAYMENT_BY_ID ="SELECT * FROM card_payments WHERE id=?";
    private static final String SELECT_PREPARED_PAYMENT =  "SELECT * FROM card_payments WHERE from_card_id=? AND prepared=TRUE";
    private static final Logger LOG = Logger.getLogger(PgCardPaymentDAO.class);

    private PgCardPaymentDAO(){

    }

    public synchronized static PgCardPaymentDAO getInstance(){
        if(instance == null){
            instance = new PgCardPaymentDAO();
        }
        return instance;
    }
    @Override
    public List<CardPayment> getSentPayments(long cardId, Connection connection) throws SQLException {
        List<CardPayment> cardPayments = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENT_BY_SENDER_CARD_ID);
        preparedStatement.setLong(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cardPayments.add(extractCardPayment(resultSet));
        }
        return cardPayments;
    }

    @Override
    public List<CardPayment> getPreparePayment(long cardId, Connection connection) throws SQLException {
        List<CardPayment> cardPayments = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PREPARED_PAYMENT);
        preparedStatement.setLong(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cardPayments.add(extractCardPayment(resultSet));
        }
        return cardPayments;
    }

    @Override
    public List<CardPayment> getReceivedPayments(long cardId, Connection connection) throws SQLException {
        List<CardPayment> cardPayments = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS_BY_RECEIVER_CARD_ID);
        preparedStatement.setLong(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            cardPayments.add(extractCardPayment(resultSet));
        }
        return cardPayments;
    }

    @Override
    public List<CardPayment> getSentExecutedPayments(long cardId, Connection connection) throws SQLException {
        List<CardPayment> cardPayments = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EXECUTED_PAYMENT_BY_SENDER_CARD_ID);
        preparedStatement.setLong(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            cardPayments.add(extractCardPayment(resultSet));
        }
        return cardPayments;
    }

    @Override
    public List<CardPayment> getReceivedExecutedPayments(long cardId, Connection connection) throws SQLException {
        List<CardPayment> cardPayments = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EXECUTED_PAYMENT_BY_RECEIVER_CARD_ID);
        preparedStatement.setLong(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            cardPayments.add(extractCardPayment(resultSet));
        }
        return cardPayments;
    }

    @Override
    public void createCardPayment(CardPayment cardPayment, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT);
        preparedStatement.setLong(1, cardPayment.getSenderCardId());
        preparedStatement.setLong(2, cardPayment.getReceiverCardId());
        preparedStatement.setDouble(3, cardPayment.getSum());
        preparedStatement.setBoolean(4, cardPayment.isPrepared());
        preparedStatement.setTimestamp(5, new Timestamp(cardPayment.getDatetime().getTime()));
        preparedStatement.execute();
    }

    @Override
    public CardPayment getCardPaymentById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractCardPayment(resultSet);
        }
        return null;
    }

    @Override
    public CardPayment extractCardPayment(ResultSet resultSet) throws SQLException {
        CardPayment cardPayment = new CardPayment();
        cardPayment.setId(resultSet.getLong("id"));
        cardPayment.setSenderCardId(resultSet.getLong("from_card_id"));
        cardPayment.setReceiverCardId(resultSet.getLong("to_card_id"));
        cardPayment.setSum(resultSet.getDouble("sum"));
        cardPayment.setPrepared(resultSet.getBoolean("prepared"));
        cardPayment.setDatetime(resultSet.getTimestamp("datetime"));
        return cardPayment;
    }
}
