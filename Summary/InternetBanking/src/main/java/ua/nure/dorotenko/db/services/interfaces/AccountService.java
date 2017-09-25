package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.Account;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.util.List;

public interface AccountService {
    List<Account> getAll() throws DatabaseException;
    List<Account> getAccountsByUserId(long userId) throws DatabaseException;
    Account getAccountById(long id) throws DatabaseException;
    void saveAccount(Account account) throws DatabaseException;
    List<String> getAccNumbers() throws DatabaseException;
    void changeAccountBlocked(long id, boolean flag) throws DatabaseException;
    void changeBalanceById(long id, double balance) throws DatabaseException;
}
