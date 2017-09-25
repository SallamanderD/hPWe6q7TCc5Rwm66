package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.entities.Account;
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
import java.util.List;

public class CreateAccountCommand extends Command {
    private static AccountService accountService = AccountServiceImpl.getInstance();
    private static Logger LOG = Logger.getLogger(CreateAccountCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command started");
        Account account = new Account();
        List<String> accNumbers = accountService.getAccNumbers();
        String name = request.getParameter("name");
        String number = AccountUtil.generateNumber();
        LOG.trace("Number -> " + number);
        while(accNumbers.contains(number)){
            number = AccountUtil.generateNumber();
            LOG.trace("Number -> " + number);
        }
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        LOG.trace("User -> " + u);
        account.setUserId(u.getId());
        account.setName(name);
        account.setNumber(number);
        accountService.saveAccount(account);
        LOG.trace("Saved -> " + account);
        request.getRequestDispatcher(Path.HOMEPAGE_COMMAND).forward(request, response);
        LOG.debug("Command finished");
    }
}
