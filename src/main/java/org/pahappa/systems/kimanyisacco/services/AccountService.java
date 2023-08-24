package org.pahappa.systems.kimanyisacco.services;



import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;

public interface AccountService {
    // Method to create and save an account for a registered user
    public Account createAccountForUser(User registeredUser);

    // Method to get the Account associated with a Register object
    public Account getAccountByRegister(User registeredUser);
    
}
