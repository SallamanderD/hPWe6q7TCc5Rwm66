package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.UserServiceImpl;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterCommand extends Command {
    private static UserServiceImpl userService = UserServiceImpl.getInstance();
    private static EmailSender emailSender = EmailSender.getInstance();
    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("REGISTER command started.");
        String fullName = request.getParameter("fullName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        User user = new User();
        user.setBanned(false);
        user.setLogin(login);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setEmail(email);
        List<String> errors = new ArrayList<>();
        if(userService.getUserByLogin(login) != null & userService.getUserByEmail(email) != null){
            errors.add(Messages.ERR_USER_ALREADY_EXIST);
        }
        if(!verify){
            errors.add("You missed a captcha");
        }
        List<String> validationErrors = UserValidator.validateUser(user);
        if(validationErrors != null){
            errors.addAll(validationErrors);
        }
        if(errors.size() != 0){
            LOG.error(Messages.ERR_USER_VALIDATING + errors.toString());
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            request.getRequestDispatcher(Path.REGISTER_PAGE).forward(request, response);
        } else{
            user.setRoleId(2);
            LOG.trace("User role set to client");
            HttpSession session = request.getSession();
            String code = UserValidator.generateCode();
            session.setAttribute("code", code);
            emailSender.sendCode(user.getEmail(), code);
            LOG.trace("Code -> " + code);
            session.setAttribute("user", user);
            response.sendRedirect(Path.ACTIVATION_PAGE);
        }

    }
}
