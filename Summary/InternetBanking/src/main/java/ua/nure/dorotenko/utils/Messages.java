package ua.nure.dorotenko.utils;

public class Messages {

    public static final String ERR_OBTAIN_CONNECTION = "Cannot obtain connection to database.";
    public static final String ERR_OBTAIN_DS = "Cannot obtain datasource.";


    public static final String ERR_OBTAIN_ALL_ACCOUNTS = "Cannot obtain accounts from database.";
    public static final String ERR_OBTAIN_ACCOUNT_BY_ID = "Cannot obtain account by its id.";
    public static final String ERR_CREATE_ACCOUNT = "Cannot save account to database.";
    public static final String ERR_OBTAIN_ACCOUNTS_BY_USER_ID = "Cannot obtain accounts by its user's id.";
    public static final String ERR_CHANGE_BALANCE = "Cannot change balance of account.";
    public static final String ERR_EXTRACT_ACCOUNT = "Cannot extract account entity.";
    public static final String ERR_OBTAIN_ACC_NUMBERS = "Cannot obtain accounts' numbers.";
    public static final String ERR_CHANGE_ACC_BLOCKED = "Cannot change account blocked by its id.";


    public static final String ERR_OBTAIN_ALL_CARDS = "Cannot obtain cards from database.";
    public static final String ERR_OBTAIN_CARD_BY_ID = "Cannot obtain card by its id.";
    public static final String ERR_OBTAIN_CARDS_BY_ACCOUNT_ID = "Cannot obtain cards by its account id.";
    public static final String ERR_CREATE_CARD = "Cannot save card to database.";
    public static final String ERR_EXTRACT_CARD = "Cannot extract card entity.";
    public static final String ERR_OBTAIN_CARDS_BY_USER = "Cannot obtain cards from database by its User.";
    public static final String ERR_CHANGE_PIN = "Cannot change card's pin with this id.";
    public static final String ERR_DELETE_CARD_BY_ID = "Cannot delete card by its id.";
    public static final String ERR_OBTAIN_CARD_BY_NUMBER = "Cannot obtain card by its number.";
    public static final String ERR_PIN = "Wrong PIN code.";

    public static final String ERR_OBTAIN_SENDED_PAYMENT = "Cannot obtain sended payment by card id.";
    public static final String ERR_OBTAIN_RECEIVED_PAYMENT = "Cannot obtain received payment by card id.";
    public static final String ERR_CREATE_PAYMENT = "Cannot save card payment to database.";
    public static final String ERR_OBTAIN_PAYMENT_BY_ID = "Cannot obtain payment by its id.";
    public static final String ERR_EXTRACT_PAYMENT = "Cannot save payment by to database.";
    public static final String ERR_EXECUTE_PAYMENT = "Cannot execute payment with such data.";
    public static final String ERR_PAYMENT_BTW_ACCS = "Cannot execute payment between cards of one account.";
    public static final String ERR_OBTAIN_PREPARE_PAYMENTS = "Cannot obtain prepared payments.";
    public static final String ERR_BALANCE = "Less balance.";


    public static final String ERR_OBTAIN_ALL_ROLES = "Cannot obtain roles from database.";
    public static final String ERR_OBTAIN_ROLE_BY_ID = "Cannot obtain role by its id.";
    public static final String ERR_EXTRACT_ROLE = "Cannot extract role entity.";


    public static final String ERR_OBTAIN_REQUEST_BY_ID = "Cannot obtain unblock request by its id.";
    public static final String ERR_OBTAIN_ALL_REQUEST = "Cannot obtain unblock request from database.";
    public static final String ERR_CHANGE_REQUEST_STATUS = "Cannot change unblock request satisfied status by its id.";
    public static final String ERR_EXTRACT_REQUEST = "Cannot extract unblock request entity.";
    public static final String ERR_SAVE_REQUEST = "Cannot save request to database.";


    public static final String ERR_OBTAIN_ALL_USERS = "Cannot obtain all users from database.";
    public static final String ERR_CHANGE_USER_ROLE = "Cannot change user role by its id.";
    public static final String ERR_CHANGE_USER_BANNED = "Cannot change user ban status by its id.";
    public static final String ERR_OBTAIN_USER_BY_ID = "Cannot obtain user by its id.";
    public static final String ERR_DELETE_USER_BY_ID = "Cannot delete user from database by its id.";
    public static final String ERR_OBTAIN_USER_BY_EMAIL = "Cannot obtain user by its email.";
    public static final String ERR_CREATE_USER = "Cannot save user to database.";
    public static final String ERR_EXTRACT_USER = "Cannot extract user entity.";
    public static final String ERR_OBTAIN_USER_BY_LOGIN = "Cannot obtain user by its login.";
    public static final String ERR_USER_ALREADY_EXIST = "User with such login or email already exists.";
    public static final String ERR_USER_VALIDATING = "There are some errors while validating user";

    public static final String ERR_OBTAIN_PROPS = "Error while obtain properties.";
    public static final String ERR_UNKNOWN_DB_PROPS = "Unknown database properties. Load default implementations.";

    public static final String ERR_VALID_USERNAME = "Username must be from 8 to 25 symbols and contains latina letters, digits and '_'.";
    public static final String ERR_VALID_PASSWD = "Password must be from 8 to 25 symbols and contains at least one lower case letter, one upper case letter, one digit and one non-word character.";
    public static final String ERR_VALID_TEL = "Telephone number must start from +380 and contains 13 symbols.";
    public static final String ERR_VALID_EMAIL = "Incorrect email.";

    public static final String ERR_PERMISSION = "You do not have permission for requested item.";


    public static final String ERR_SEND_EMAIL = "Cannot sent email.";

    public static final String WRONG_ARGUMENTS = "Wrong request, check the correctness of the entered data.";

    private Messages(){

    }


}
