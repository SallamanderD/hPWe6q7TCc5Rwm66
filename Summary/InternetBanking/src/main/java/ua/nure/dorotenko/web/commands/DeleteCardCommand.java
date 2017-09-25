package ua.nure.dorotenko.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.services.CardServiceImpl;
import ua.nure.dorotenko.db.services.interfaces.CardService;
import ua.nure.dorotenko.entities.Card;
import ua.nure.dorotenko.exceptions.ApplicationException;
import ua.nure.dorotenko.utils.Messages;
import ua.nure.dorotenko.utils.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteCardCommand.class);
    private static CardService cardService = CardServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ApplicationException {
        LOG.debug("Command starts.");
        long cardId;
        try{
            cardId = Long.parseLong(request.getParameter("cardId"));
        } catch (NumberFormatException ex){
            LOG.error(Messages.WRONG_ARGUMENTS, ex);
            request.setAttribute("error", Messages.WRONG_ARGUMENTS);
            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
            return;
        }
        String PIN = request.getParameter("PIN");
        Card card = cardService.getCardById(cardId);
        if(card.getPIN().equals(DigestUtils.md5Hex(PIN))){
            cardService.deleteCardById(cardId);
            response.sendRedirect(Path.CARDS_COMMAND);
        } else{
            String msg = "Wrong PIN code. Try again.";
            request.setAttribute("cardId", cardId);
            request.setAttribute("error", msg);
            request.getRequestDispatcher(Path.DELETING_CARD_PAGE).forward(request, response);
        }
        LOG.debug("Command finishes");
    }
}
