package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface CardPaymentService {
    List<CardPayment> getSentPayments(long cardId) throws DatabaseException;
    List<CardPayment> getReceivedPayments(long cardId) throws DatabaseException;
    List<CardPayment> getExecutedSentPayments(long cardId) throws DatabaseException;
    List<CardPayment> getExecutedReceivedPayments(long cardId) throws DatabaseException;
    List<CardPayment> getPreparePayment(long cardId) throws DatabaseException;
    boolean saveCardPayment(CardPayment cardPayment) throws DatabaseException;
    CardPayment getCardPaymentById(long id) throws DatabaseException;
}
