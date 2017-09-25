import model.Vote;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(VoteServlet.class);
    List<Vote> votes;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoGet executing.");
        String sportName = request.getParameter("sport");
        String login = request.getParameter("login");
        if(!checkVoting(login)){
            logger.error("User " + login + " has already voted.");
            request.setAttribute("error", "This user complete voting already.");
            request.setAttribute("variants", votes);
            request.getRequestDispatcher("Vote.jsp").forward(request, response);
            return;
        }
        for(Vote vote : votes){
            if(vote.getName().equals(sportName)){
                vote.setCount(vote.getCount() + 1);
                vote.getLogins().add(login);
                logger.info("User " + login + " vote for " + sportName + ". ");
            }
        }
        request.setAttribute("variants", votes);
        request.getRequestDispatcher("VoteResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoGet executing...");
        request.setAttribute("variants", votes);
        request.getRequestDispatcher("Vote.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        votes = new ArrayList<>();
        String sportString = getServletContext().getInitParameter("list");
        List<String> sportList = Arrays.asList(sportString.split("\\s+"));
        for(String s : sportList){
            votes.add(new Vote(new ArrayList<>(), s, 0));
        }
    }

    public boolean checkVoting(String login){
        for(Vote v : votes){
            for(String s : v.getLogins()){
                if(login.equals(s)){
                    return false;
                }
            }
        }
        return true;
    }
}
