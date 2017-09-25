package ua.nure.dorotenko.web.commands;

import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLangCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        HttpSession session = request.getSession();
        String a = request.getParameter("language");
        session.setAttribute("language", a);
        response.sendRedirect("/jsp/profile.jsp");
    }
}
