package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
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


public class PaymentHistoryCommand extends Command {
    private static CardService cardService = CardServiceImpl.getInstance();
    private static CardPaymentService cardPaymentService = CardPaymentServiceImpl.getInstance();
    private static final Logger LOG = Logger.getLogger(PaymentHistoryCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        User u = (User) request.getSession().getAttribute("user");
        List<Card> cards = cardService.getCardsByUserId(u.getId());
        List<CardPayment> receivedPayment = new ArrayList<>();
        List<CardPayment> sentPayment = new ArrayList<>();
        for(Card c : cards){
            receivedPayment.addAll(cardPaymentService.getExecutedReceivedPayments(c.getId()));
            sentPayment.addAll(cardPaymentService.getExecutedSentPayments(c.getId()));
        }
        if(receivedPayment.size() != 0){
            List<Card> receiveTargetCards = new ArrayList<>();
            List<Card> receiveSenderCards = new ArrayList<>();
            for(CardPayment cardPayment : receivedPayment){
                receiveTargetCards.add(cardService.getCardById(cardPayment.getReceiverCardId()));
                receiveSenderCards.add(cardService.getCardById(cardPayment.getSenderCardId()));
            }
            request.setAttribute("receiveTargetCards", receiveTargetCards);
            request.setAttribute("receiveSenderCards", receiveSenderCards);
            request.setAttribute("receivePayment", receivedPayment);
        }
        if(sentPayment.size() != 0){
            List<Card> sentTargetCards = new ArrayList<>();
            List<Card> sentSenderCards = new ArrayList<>();
            for(CardPayment cardPayment : sentPayment){
                sentTargetCards.add(cardService.getCardById(cardPayment.getReceiverCardId()));
                sentSenderCards.add(cardService.getCardById(cardPayment.getSenderCardId()));
            }
            request.setAttribute("sentTargetCards", sentTargetCards);
            request.setAttribute("sentSenderCards", sentSenderCards);
            request.setAttribute("sentPayment", sentPayment);
        }
        request.getRequestDispatcher(Path.PAYMENT_HISTORY_PAGE).forward(request, response);
        LOG.debug("Command finishes.");
    }
}
