import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/textInput")
public class TextServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(TextServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("DoGet executing...");
        req.setAttribute("text", req.getParameter("text"));
        System.out.println("==> " + req.getParameter("text"));
        req.getRequestDispatcher("TextResult.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("DoPost executing...");
        req.setAttribute("text", req.getParameter("text"));
        System.out.println("==> " + req.getParameter("text"));
        req.getRequestDispatcher("TextResult.jsp").forward(req, resp);
    }
}
