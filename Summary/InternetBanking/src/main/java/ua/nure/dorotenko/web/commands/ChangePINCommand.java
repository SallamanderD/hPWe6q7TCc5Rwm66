package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.CardUtil;
import ua.nure.dorotenko.utils.EmailSender;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePINCommand extends Command {
    private static CardService cardService = CardServiceImpl.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();
    private static EmailSender emailSender = EmailSender.getInstance();
    private final static Logger LOG = Logger.getLogger(ChangePINCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, ApplicationException {
        LOG.debug("Command starts");
        String cardId = request.getParameter("cardId");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        long id;
        try{
            id = Long.parseLong(cardId);
        } catch (NumberFormatException ex){
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        Card card = cardService.getCardById(id);
        Account account = accountService.getAccountById(card.getAccountId());
        if(u.getId() != account.getUserId()){
            LOG.error(Messages.ERR_PERMISSION);
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        String newPin = CardUtil.generatePIN();
        emailSender.sendPin(u.getEmail(), newPin, cardService.getCardById(id).getNumber());
        cardService.changePINById(id, DigestUtils.md5Hex(newPin));
        response.sendRedirect(Path.CARDS_COMMAND);
        LOG.debug("Command finishes");
    }
}
