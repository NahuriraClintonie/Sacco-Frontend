package org.pahappa.systems.kimanyisacco.services;

import java.util.List;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.models.User;

public interface LoginService {
    
    public boolean authenticate(String username, String password);

    public User authenticateAndGetUser(String username, String password);

    public Account getAccountByUserId(long userId);

    public List<Transactions> getTransactionsByAccountNumber(String accountNumber);

    public int getTotalTransactionsByAccountNumber(String accountNumber);

    public Double getMostRecentWithdrawAmountByAccountNumber(String accountNumber);

    public Double getMostRecentDepositAmountByAccountNumber(String accountNumber);
}
