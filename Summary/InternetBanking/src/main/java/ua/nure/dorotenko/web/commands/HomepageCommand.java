package ua.nure.dorotenko.web.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.AccountServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.AccountService;
import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.entities.User;
import ua.nure.dorotenko.exceptions.DatabaseException;
import ua.nure.dorotenko.utils.AccountUtil;
import ua.nure.dorotenko.utils.Path;
import ua.nure.dorotenko.utils.PrivatAPI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomepageCommand extends Command {
    private static Logger LOG = Logger.getLogger(HomepageCommand.class);
    private static AccountService accountService = AccountServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Account> accounts = accountService.getAccountsByUserId(user.getId());
        String sort = request.getParameter("sort");
        if(sort != null){
            switch (sort){
                case "balance":
                    accounts = AccountUtil.sortByBalance(accounts);
                    break;
                case "name":
                    accounts = AccountUtil.sortByName(accounts);
                    break;
                case "number":
                    accounts = AccountUtil.sortByNumber(accounts);
                    break;
            }
        } else{
            accounts = AccountUtil.sortByName(accounts);
        }
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher(Path.HOME_PAGE).forward(request, response);
        LOG.debug("Command finished");
    }
}
