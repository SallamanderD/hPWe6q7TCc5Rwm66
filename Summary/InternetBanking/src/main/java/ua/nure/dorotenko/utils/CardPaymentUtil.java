package ua.nure.dorotenko.utils;

import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.util.Comparator;

public class CardPaymentUtil {
    private static CardService cardService = CardServiceImpl.getInstance();
    private static final Comparator<CardPayment> DATE_COMPARATOR = new Comparator<CardPayment>() {
        @Override
        public int compare(CardPayment cardPayment, CardPayment t1) {
            return cardPayment.getDatetime().compareTo(t1.getDatetime());
        }
    };
}
