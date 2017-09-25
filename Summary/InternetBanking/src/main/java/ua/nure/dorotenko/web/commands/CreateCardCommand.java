package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.CardUtil;
import ua.nure.dorotenko.utils.EmailSender;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCardCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    private static CardService cardService = CardServiceImpl.getInstance();
    private static EmailSender emailSender = EmailSender.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts");
        String accId = request.getParameter("accountId");
        String PIN = request.getParameter("PIN");
        User u = (User) request.getSession().getAttribute("user");
        if(PIN == null){
            LOG.error(Messages.WRONG_ARGUMENTS);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        long id;
        try{
            id = Long.parseLong(accId);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        if(accountService.getAccountById(id).getUserId() != u.getId()){
            LOG.error(Messages.ERR_PERMISSION);
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        Card c = new Card();
        c.setPIN(DigestUtils.md5Hex(PIN));
        c.setAccountId(Long.parseLong(accId));
        c.setNumber(CardUtil.generateNumber());
        cardService.saveCard(c);
        emailSender.sendPin(u.getEmail(), c.getPIN(), c.getNumber());
        response.sendRedirect(Path.CARDS_COMMAND);
    }
}
