package ua.nure.dorotenko.web.commands;

import ua.nure.dorotenko.db.services.CardPaymentServiceImpl;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.CardPaymentService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreparedPaymentPageCommand extends Command {
    private static CardPaymentService cardPaymentService = CardPaymentServiceImpl.getInstance();
    private static CardService cardService = CardServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        User u = (User) request.getSession().getAttribute("user");
        List<Card> cards = cardService.getCardsByUserId(u.getId());
        List<CardPayment> preparePayments = new ArrayList<>();
        for(Card c : cards){
            preparePayments.addAll(cardPaymentService.getPreparePayment(c.getId()));
        }
        if(preparePayments.size() != 0){
            List<Card> senderCards = new ArrayList<>();
            List<Card> receiverCards = new ArrayList<>();
            for(CardPayment cardPayment : preparePayments){
                senderCards.add(cardService.getCardById(cardPayment.getSenderCardId()));
                receiverCards.add(cardService.getCardById(cardPayment.getReceiverCardId()));
            }
            request.setAttribute("preparePayments", preparePayments);
            request.setAttribute("senderCards", senderCards);
            request.setAttribute("receiverCards", receiverCards);
        }
        request.getRequestDispatcher(Path.PREPARE_PAYMENTS_PAGE).forward(request, response);
    }
}
