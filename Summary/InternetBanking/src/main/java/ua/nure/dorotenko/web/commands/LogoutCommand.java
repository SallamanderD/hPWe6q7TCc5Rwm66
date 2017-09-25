package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession(false);
        if(session != null){
            LOG.trace("Session ->" + session.toString());
            session.invalidate();
            LOG.trace("Session destructed");
            request.getRequestDispatcher(Path.LOGIN_PAGE).forward(request, response);
        }
        LOG.debug("Command finishes");
    }
}
