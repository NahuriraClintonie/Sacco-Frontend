package org.pahappa.systems.kimanyisacco.services;
import org.pahappa.systems.kimanyisacco.models.Account;

public interface TransactionService {

    // Method to save the deposit transaction
    public void saveDepositTransaction(Account account, double amount);

    // Method to save the withdraw transaction
    public void saveWithdrawTransaction(Account account, double amount);

    // Method to get the most recent withdrawal amount for a given account
    public Double getMostRecentWithdrawal(Account account);

    // Method to get the most recent withdrawal amount for a given account
    public void updateAccountBalance(Long userId, double amount);

    // Method to get the most recent withdrawal amount for a given account
    public void updateAccountBalanceAfterWithdraw(Long userId, double amount);

    // Method to get the most recent withdrawal amount for a given account
    public Double getAccountBalanceByUserId(Long userId);
}
