package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.CardPaymentServiceImpl;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardPaymentService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.CardPayment;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentCommand extends Command {
    private static final Logger LOG = Logger.getLogger(PaymentCommand.class);
    private static CardPaymentService cardPaymentService = CardPaymentServiceImpl.getInstance();
    private static CardService cardService = CardServiceImpl.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        long cardId;
        double sum;
        try{
            cardId = Long.parseLong(request.getParameter("card"));
            sum = Double.parseDouble(request.getParameter("sum"));
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        String PIN = request.getParameter("PIN");
        String cardToNumber = request.getParameter("cardTo");
        if(PIN == null || PIN.isEmpty() || cardToNumber == null || cardToNumber.isEmpty()){
            LOG.error(Messages.WRONG_ARGUMENTS);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        boolean prepared = Boolean.parseBoolean(request.getParameter("prepared"));
        Card cardFrom = cardService.getCardById(cardId);
        Card cardTo = cardService.getCardByNumber(cardToNumber);
        Account accountFrom = accountService.getAccountById(cardFrom.getAccountId());
        User u = (User) request.getSession().getAttribute("user");
        if(accountFrom.getUserId() != u.getId()){
            LOG.error(Messages.ERR_PERMISSION);
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        List<String> errors = new ArrayList<>();
        if(accountFrom.getBalance() < sum && !prepared){
            errors.add(Messages.ERR_BALANCE);
        }
        if(cardFrom.getAccountId() == cardTo.getAccountId()) {
            errors.add(Messages.ERR_PAYMENT_BTW_ACCS);
        }
        if(!cardFrom.getPIN().equals(DigestUtils.md5Hex(PIN))){
            errors.add(Messages.ERR_PIN);
        }
        if(errors.size() != 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("cardId", cardId);
            request.setAttribute("cards", cardService.getCardsByUserId(u.getId()));
            request.getRequestDispatcher(Path.MAKE_PAYMENTS_PAGE).forward(request, response);
        } else{
            CardPayment cardPayment = new CardPayment();
            cardPayment.setSenderCardId(cardFrom.getId());
            cardPayment.setReceiverCardId(cardTo.getId());
            cardPayment.setSum(sum);
            cardPayment.setPrepared(prepared);
            cardPaymentService.saveCardPayment(cardPayment);
            response.sendRedirect(Path.HOMEPAGE_COMMAND);
        }
        LOG.debug("Command finishes");
    }
}
