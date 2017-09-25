import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/authentification")
public class AuthentificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("login") != null){
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        } else{
            req.getRequestDispatcher("authentificate.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/Practice10db");
            Connection con = ds.getConnection();
            Statement s = con.createStatement();
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            String name = req.getParameter("login");
            String password = req.getParameter("pass");
            ResultSet rs = s.executeQuery("SELECT * FROM users WHERE login='" + name + "' AND password='" + password + "';");
            if(rs.next()){
                session.setAttribute("user", rs.getString(2));
                rs = s.executeQuery("SELECT * FROM roles WHERE id='" + rs.getString(1) + "';");
                rs.next();
                session.setAttribute("role", rs.getString(2));
                resp.sendRedirect("/");
                return;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
