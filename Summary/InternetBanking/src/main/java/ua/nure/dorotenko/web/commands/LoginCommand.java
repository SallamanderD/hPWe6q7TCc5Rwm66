package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.RoleServiceImpl;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.RoleService;
import ua.nure.dorotenko.db.services.interfaces.UserService;
import ua.nure.dorotenko.entities.Role;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;
import ua.nure.dorotenko.utils.VerifyRecaptcha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static UserService userService = UserServiceImpl.getInstance();
    private static RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        HttpSession httpSession = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        LOG.trace("Login -> " + login);
        List<String> errors = new ArrayList<>();
        if(login == null || password == null || login.isEmpty() || password.isEmpty()){
            errors.add("Login or password cannot be empty.");
        }
        User user = userService.getUserByLogin(login);
        if(!verify){
            errors.add("You missed a captcha");
        }
        if(user == null || !DigestUtils.md5Hex(password).equals(user.getPassword())){
            errors.add("Cannot find user in database with such credentials.");
        }
        if(user != null && user.isBanned()){
            errors.add("Such user is banned.");
        }
        if(errors.size() != 0){
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(Path.LOGIN_PAGE).forward(request, response);
            return;
        }
        LOG.trace("User -> " + user);
        httpSession.setAttribute("user", user);
        LOG.trace("Set attribute to session " + user);
        Role role = roleService.getRoleById(user.getRoleId());
        httpSession.setAttribute("userRole", role.getName());
        LOG.trace("Set attribute to session " + role.getName());
        LOG.info("User " + user + " logged as " + role.getName());
        LOG.debug("Command finishes.");
        response.sendRedirect(Path.HOMEPAGE_COMMAND);
    }
}
