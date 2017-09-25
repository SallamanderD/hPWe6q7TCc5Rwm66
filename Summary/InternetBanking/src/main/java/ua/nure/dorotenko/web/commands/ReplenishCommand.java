package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReplenishCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ReplenishCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        String account = request.getParameter("account");
        String money = request.getParameter("sum");
        long accountId;
        double sum;
        try{
            accountId = Long.parseLong(account);
            sum = Double.parseDouble(money);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        User u = (User) request.getSession().getAttribute("user");
        LOG.trace("User -> " + u);
        Account acc = accountService.getAccountById(accountId);
        if(u.getId() != acc.getUserId()){
            LOG.error(Messages.ERR_PERMISSION);
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        accountService.changeBalanceById(acc.getId(), acc.getBalance() + sum);
        response.sendRedirect(Path.HOMEPAGE_COMMAND);
        LOG.debug("Command finishes.");
    }
}
