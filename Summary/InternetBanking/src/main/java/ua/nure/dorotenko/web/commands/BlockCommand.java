package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockCommand extends Command {
    private final static Logger LOG = Logger.getLogger(BlockCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command started");
        long accId;
        try{
            accId = Long.parseLong(request.getParameter("accId"));
        } catch (NumberFormatException ex){
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        User u = (User) request.getSession().getAttribute("user");
        if(u.getId() != accountService.getAccountById(accId).getUserId()){
            LOG.error(Messages.ERR_PERMISSION);
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        accountService.changeAccountBlocked(accId, true);
        response.sendRedirect(Path.HOMEPAGE_COMMAND);
        LOG.debug("Command finished");
    }
}
