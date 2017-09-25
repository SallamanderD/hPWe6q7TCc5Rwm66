package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.UnblockRequestServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.UnblockRequestService;
import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeAccountBlockedCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeAccountBlockedCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();
    private static UnblockRequestService unblockRequestService = UnblockRequestServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts");
        String id = request.getParameter("accId");
        String flag = request.getParameter("block");
        long accountId;
        boolean block;
        try{
            accountId = Long.parseLong(id);
            block = Boolean.parseBoolean(flag);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        accountService.changeAccountBlocked(accountId, block);
        List<UnblockRequest> unblockRequests = unblockRequestService.getAllUnsatisfiedUnblockRequest();
        for(UnblockRequest unblockRequest : unblockRequests){
            if(unblockRequest.getAccountId() == accountId){
                unblockRequestService.changeUnblockRequestStatusToSatisfied(unblockRequest.getId());
            }
        }
        response.sendRedirect(Path.ADMIN_COMMAND);
        LOG.debug("Command finishes");
    }
}
