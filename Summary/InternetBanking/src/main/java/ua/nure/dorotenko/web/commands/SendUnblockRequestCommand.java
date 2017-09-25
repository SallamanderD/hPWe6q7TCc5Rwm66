package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.UnblockRequestServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.db.services.interfaces.UnblockRequestService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SendUnblockRequestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(SendUnblockRequestCommand.class);
    private static UnblockRequestService unblockRequestService = UnblockRequestServiceImpl.getInstance();
    private static AccountService accountService = AccountServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        String id = request.getParameter("accId");
        long accountId;
        try{
            accountId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        Account account = accountService.getAccountById(accountId);
        User u = (User) request.getSession().getAttribute("user");
        if(u.getId() != account.getUserId()){
            request.setAttribute("error", Messages.ERR_PERMISSION);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        List<UnblockRequest> unblockRequests = unblockRequestService.getAllUnsatisfiedUnblockRequest();
        for(UnblockRequest req : unblockRequests){
            if(req.getAccountId() == accountId){
                response.sendRedirect(Path.HOMEPAGE_COMMAND);
                return;
            }
        }
        UnblockRequest unblockRequest = new UnblockRequest();
        unblockRequest.setSatisfied(false);
        unblockRequest.setDatetime(new Date());
        unblockRequest.setAccountId(accountId);
        unblockRequestService.saveUnblockRequest(unblockRequest);
        response.sendRedirect(Path.HOMEPAGE_COMMAND);
    }
}
