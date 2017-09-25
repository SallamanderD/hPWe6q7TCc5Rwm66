import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/names")
public class NamesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("inputName.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        List<String> names = (List<String>) session.getAttribute("names");
        if(names == null){
            names = new ArrayList<String>();
        }
        if(!names.contains(name)){
            names.add(name);
            session.setAttribute("names", names);
            resp.sendRedirect("viewNames.jsp");
            return;
        }
        resp.sendRedirect("/names");
    }
}
