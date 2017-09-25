package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardPageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteCardPageCommand.class);
    private static CardService cardService = CardServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        String cardId = request.getParameter("cardId");
        long id;
        try{
            id = Long.parseLong(cardId);
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        request.setAttribute("card", cardService.getCardById(id));
        request.getRequestDispatcher(Path.DELETING_CARD_PAGE).forward(request, response);
        LOG.debug("Command finishes.");
    }
}
