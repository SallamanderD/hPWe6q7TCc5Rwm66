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
import ua.nure.dorotenko.utils.AccountUtil;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CardsCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CardsCommand.class);
    private static CardService cardService = CardServiceImpl.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command started");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        LOG.trace("User -> " + u);
        String accId = request.getParameter("accId");
        List<Account> accounts = new ArrayList<>();
        if(accId != null){
            long accountId;
            try{
                accountId = Long.parseLong(accId);
            } catch (NumberFormatException ex){
                request.setAttribute("error", Messages.WRONG_ARGUMENTS);
                request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
                return;
            }
            Account account = accountService.getAccountById(accountId);
            if(account.getUserId() != u.getId()){
                LOG.error(Messages.ERR_PERMISSION);
                request.setAttribute("error", Messages.ERR_PERMISSION);
                request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
                return;
            }
            accounts.add(account);
        } else{
            accounts = accountService.getAccountsByUserId(u.getId());
            accounts = AccountUtil.sortByName(accounts);
        }
        List<List<Card>> cards = new ArrayList<>();
        for(Account account : accounts){
            cards.add(cardService.getCardsByAccountId(account.getId()));
        }
        request.setAttribute("accounts", accounts);
        request.setAttribute("cards", cards);
        request.getRequestDispatcher(Path.CARDS_PAGE).forward(request, response);
        LOG.debug("Command finished");
    }
}
