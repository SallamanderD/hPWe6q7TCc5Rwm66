package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeAdminCommand extends Command {
    private static UserService userService = UserServiceImpl.getInstance();
    private static final Logger LOG = Logger.getLogger(MakeAdminCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.error("Command starts");
        String id = request.getParameter("userId");
        long userId;
        try{
            userId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        userService.changeUserRole(userId, 1);
        response.sendRedirect(Path.ADMIN_COMMAND);
        LOG.error("Command finished");
    }
}
