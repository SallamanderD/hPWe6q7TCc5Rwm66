package ua.nure.dorotenko.web;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Path;
import ua.nure.dorotenko.web.commands.Command;
import ua.nure.dorotenko.web.commands.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }



    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Controller starts");
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);
        try {
            command.execute(request, response);
            return;
        } catch (ApplicationException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher(Path.ERROR_PAGE).forward(request,response);
    }
}
