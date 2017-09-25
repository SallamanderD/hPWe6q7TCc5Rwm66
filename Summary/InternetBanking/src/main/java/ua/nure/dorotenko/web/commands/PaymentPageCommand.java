package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.CardUtil;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentPageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(PaymentPageCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();
    private static CardService cardService = CardServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        String cardId = request.getParameter("cardId");
        String cardNumber = request.getParameter("cardNumber");
        String sum = request.getParameter("sum");
        User u = (User) request.getSession().getAttribute("user");
        List<Card> cards = cardService.getCardsByUserId(u.getId());
        if(cardId != null){
            long id;
            try{
                id = Long.parseLong(cardId);
                request.setAttribute("cardId", id);
            } catch (NumberFormatException ex){
                LOG.error(Messages.WRONG_ARGUMENTS, ex);
                request.setAttribute("error", Messages.WRONG_ARGUMENTS);
                request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
                return;
            }
        }
        if(cardNumber != null && sum != null){
            request.setAttribute("cardNumber", cardNumber);
            request.setAttribute("sum", sum);
        }
        request.setAttribute("cards", cards);
        request.getRequestDispatcher(Path.MAKE_PAYMENTS_PAGE).forward(request, response);
        LOG.debug("Command finishes");
    }
}
