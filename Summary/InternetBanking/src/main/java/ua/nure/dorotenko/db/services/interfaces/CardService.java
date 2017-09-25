package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface CardService {
    void saveCard(Card card) throws DatabaseException;
    Card getCardById(long id) throws DatabaseException;
    List<Card> getCardsByAccountId(long id) throws DatabaseException;
    void changePINById(long id, String PIN) throws DatabaseException;
    void deleteCardById(long id) throws DatabaseException;
    Card getCardByNumber(String number) throws DatabaseException;
    List<Card> getCardsByUserId(long id) throws DatabaseException;
}
