package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReplenishPageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ReplenishPageCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        User u = (User) request.getSession().getAttribute("user");
        LOG.trace("User -> " + u);
        List<Account> accountList = accountService.getAccountsByUserId(u.getId());
        request.setAttribute("accounts", accountList);
        request.getRequestDispatcher(Path.REPLENISH_PAGE).forward(request, response);
        LOG.debug("Command finishes.");
    }
}
