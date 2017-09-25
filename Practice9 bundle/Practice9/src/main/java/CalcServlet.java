import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/calculate")
public class CalcServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CalcServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("DoGet executing...");
        int x = Integer.valueOf(req.getParameter("x"));
        int y = Integer.valueOf(req.getParameter("y"));
        String operation = req.getParameter("op");
        int result = 0;
        switch (operation){
            case "plus":
                result = x + y;
                break;
            case "minus":
                result = x - y;
                break;
            case "mult":
                result = x * y;
                break;
            case "divide":
                result = x / y;
                break;
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher("ResultCalc.jsp").forward(req, resp);
    }
}
