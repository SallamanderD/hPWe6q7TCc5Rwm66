package ua.nure.dorotenko.utils;

import ua.nure.dorotenko.entities.Account;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AccountUtil {
    private static final Comparator<Account> BALANCE_COMPARATOR = new Comparator<Account>() {
        @Override
        public int compare(Account account, Account account2) {
            if(account.getBalance() > account2.getBalance()){
                return -1;
            } else if(account.getBalance() < account2.getBalance()){
                return 1;
            }
            return 0;
        }
    };

    private static final Comparator<Account> NAME_COMPARATOR = new Comparator<Account>() {
        @Override
        public int compare(Account account, Account account2) {
            return account.getName().compareTo(account2.getName());
        }
    };

    private static final Comparator<Account> NUMBER_COMPARATOR = new Comparator<Account>() {
        @Override
        public int compare(Account account, Account account2) {
            return account.getNumber().compareTo(account2.getNumber());
        }
    };


    /**
     * Generate a account number.
     * @return account number as string
     */
    public static String generateNumber(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        sb.append(".");
        for(int i = 0; i < 2; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        sb.append(".");
        for(int i = 0; i < 3; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        sb.append(".");
        sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        sb.append(".");
        for(int i = 0; i < 4; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        sb.append(".");
        for(int i = 0; i < 6; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return sb.toString();
    }

    public static List<Account> sortByBalance(List<Account> accounts){
        accounts.sort(BALANCE_COMPARATOR);
        return accounts;
    }

    public static List<Account> sortByName(List<Account> accounts){
        accounts.sort(NAME_COMPARATOR);
        return accounts;
    }

    public static List<Account> sortByNumber(List<Account> accounts){
        accounts.sort(NUMBER_COMPARATOR);
        return accounts;
    }
}
