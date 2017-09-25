package ua.nure.dorotenko.web.commands;

import ua.nure.dorotenko.exceptions.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command extends HttpServlet {
    private static final long serialVersionUID = 8879403039606311780L;

    public abstract void execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException, ApplicationException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
