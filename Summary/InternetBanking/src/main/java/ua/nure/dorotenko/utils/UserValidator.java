package ua.nure.dorotenko.utils;

import ua.nure.dorotenko.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String LOGIN_REGEXP = "^\\w{8,25}$";
    private static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).{8,25}$";
    private static final String TELEPHONE_REGEXP = "^\\+380(95|66|50|99|67|68)\\d{7}$";
    private static final String EMAIL_REGEXP = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    private static String validateLogin(String login){
        Pattern p = Pattern.compile(LOGIN_REGEXP);
        Matcher m = p.matcher(login);
        if(!m.matches()){
            return Messages.ERR_VALID_USERNAME;
        }
        return "";
    }

    private static String validatePassword(String password){
        Pattern p = Pattern.compile(PASSWORD_REGEXP);
        Matcher m = p.matcher(password);
        if(!m.matches()){
            return Messages.ERR_VALID_PASSWD;
        }
        return "";
    }

    private static String validateTelephone(String telephone){
        Pattern p = Pattern.compile(TELEPHONE_REGEXP);
        Matcher m = p.matcher(telephone);
        if(!m.matches()){
            return Messages.ERR_VALID_TEL;
        }
        return "";
    }

    private static String validateEmail(String email){
        Pattern p = Pattern.compile(EMAIL_REGEXP);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            return Messages.ERR_VALID_EMAIL;
        }
        return "";
    }

    public static String generateCode(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 5; i++){
            stringBuilder.append((char) ThreadLocalRandom.current().nextInt(65, 91));
        }
        return stringBuilder.toString();
    }

    public static List<String> validateUser(User user){
        List<String> errors = new ArrayList<>();
        String error;
        error = validateLogin(user.getLogin());
        if(!error.isEmpty()){
            errors.add(error);
        }
        error = validateTelephone(user.getTelephone());
        if(!error.isEmpty()){
            errors.add(error);
        }
        error = validatePassword(user.getPassword());
        if(!error.isEmpty()){
            errors.add(error);
        }
        error = validateEmail(user.getEmail());
        if(!error.isEmpty()){
            errors.add(error);
        }
        if(errors.size() == 0){
            return null;
        }
        return errors;
    }
}
