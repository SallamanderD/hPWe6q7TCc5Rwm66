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

public class ChangeUserBannedCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeUserBannedCommand.class);
    private static UserService userService = UserServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts");
        String id = request.getParameter("userId");
        String flag = request.getParameter("banned");
        long userId;
        boolean banned;
        try{
            userId = Long.parseLong(id);
            banned = Boolean.parseBoolean(flag);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        userService.changeUserBanned(userId, banned);
        response.sendRedirect(Path.ADMIN_COMMAND);
        LOG.debug("Command finishes");

    }
}
