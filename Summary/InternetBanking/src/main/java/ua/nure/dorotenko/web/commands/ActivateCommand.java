package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActivateCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ActivateCommand.class);
    private static UserService userService = UserServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command started");
        HttpSession session = request.getSession();
        String inputCode = request.getParameter("code");
        String code = (String) session.getAttribute("code");
        User user = (User) session.getAttribute("user");
        if(code.equals(inputCode)){
            request.setAttribute("login", user.getLogin());
            request.setAttribute("password", user.getPassword());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            LOG.trace("User password hashed: " + user.getPassword());
            userService.saveUser(user);
            request.getRequestDispatcher(Path.LOGIN_COMMAND).forward(request, response);
        } else{
            String errorMsg = "Wrong activation code.";
            request.setAttribute("error", errorMsg);
            request.getRequestDispatcher(Path.ACTIVATION_PAGE).forward(request, response);
        }
        LOG.debug("Command finished");
    }
}
