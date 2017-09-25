package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.UnblockRequestServiceImpl;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.UnblockRequestService;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPanelCommand extends Command {
    private static UserService userService = UserServiceImpl.getInstance();
    private static UnblockRequestService unblockRequestService = UnblockRequestServiceImpl.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();
    private static final Logger LOG = Logger.getLogger(AdminPanelCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts");
        List<User> users = userService.getAll();
        List<UnblockRequest> unblockRequests = unblockRequestService.getAllUnsatisfiedUnblockRequest();
        List<Account> accounts = accountService.getAll();
        request.setAttribute("users", users);
        request.setAttribute("requests", unblockRequests);
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher(Path.ADMIN_PAGE).forward(request, response);
        LOG.debug("Command finishes");
    }
}
