import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/changeName")
public class Part4 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String newName = req.getParameter("name");
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/Practice10db");
            Connection con = ds.getConnection();
            Statement s = con.createStatement();
            HttpSession session = req.getSession();
            s.execute("UPDATE users SET name='" + newName + "' WHERE login='" + session.getAttribute("user") + "';");
            resp.sendRedirect("/");
            return;
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
