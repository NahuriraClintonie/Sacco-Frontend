package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.dao.AccountDao;
import java.util.Random;

public class AccountServiceImpl {
    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDao();
    }

    // Method to create and save an account for a registered user
    public Account createAccountForUser(User registeredUser) {
        Account account = new Account();
        account.setUser(registeredUser);
        account.setAccountBalance(0.0);
        account.setAccountNumber(generateAccountNumber()); 
        return accountDao.saveAccount(account);
    }

    // Method to get the Account associated with a Register object
    public Account getAccountByRegister(User registeredUser) {
        return accountDao.getAccountByRegister(registeredUser);
    }

    // A method to generate account numbers
    private String generateAccountNumber() {
        // Pattern for account number
        String accountPattern = "ACCT-";
        
        // Generate a random 4-digit number
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generates a random number between 0 and 9999

        // Concatenate the pattern with the random number
        return accountPattern + String.format("%04d", randomNumber);
    }
}
