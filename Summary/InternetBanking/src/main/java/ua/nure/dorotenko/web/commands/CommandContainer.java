package ua.nure.dorotenko.web.commands;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("unknown", new UnknownCommand());
        commands.put("homepage", new HomepageCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("activate", new ActivateCommand());
        commands.put("createAcc", new CreateAccountCommand());
        commands.put("block", new BlockCommand());
        commands.put("cards", new CardsCommand());
        commands.put("changePIN", new ChangePINCommand());
        commands.put("deleteCardPage", new DeleteCardPageCommand());
        commands.put("deleteCard", new DeleteCardCommand());
        commands.put("createCardPage", new CreateCardPageCommand());
        commands.put("createCard", new CreateCardCommand());
        commands.put("paymentPage", new PaymentPageCommand());
        commands.put("makePayment", new PaymentCommand());
        commands.put("paymentHistory", new PaymentHistoryCommand());
        commands.put("preparedPayments", new PreparedPaymentPageCommand());
        commands.put("replenishPage", new ReplenishPageCommand());
        commands.put("replenish", new ReplenishCommand());
        commands.put("adminPanel", new AdminPanelCommand());
        commands.put("sendUnblock", new SendUnblockRequestCommand());
        commands.put("makeAdmin", new MakeAdminCommand());
        commands.put("changeBanned", new ChangeUserBannedCommand());
        commands.put("blockAccount", new ChangeAccountBlockedCommand());
        commands.put("setLang", new SetLangCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("unknownCommand");
        }
        return commands.get(commandName);
    }
}
