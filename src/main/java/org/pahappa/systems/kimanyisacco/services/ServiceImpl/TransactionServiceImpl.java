package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.dao.TransactionDao;

import java.util.Date;

public class TransactionServiceImpl {
    private TransactionDao transactionDao;

    public TransactionServiceImpl() {
        transactionDao = new TransactionDao();
    }

    public void saveDepositTransaction(Account account, double amount) {
        Transactions transaction = new Transactions();
        transaction.setAccount(account);
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);
        transaction.setDate(new Date());

        double newBalance = account.getAccountBalance() + amount;
        account.setAccountBalance(newBalance);

        // Save the updated account and transaction
        transactionDao.saveTransaction(transaction);
        transactionDao.updateAccount(account);
    }

    public void saveWithdrawTransaction(Account account, double amount) {
        Transactions transaction = new Transactions();
        transaction.setAccount(account);
        transaction.setTransactionType("Withdraw");
        transaction.setAmount(amount);
        transaction.setDate(new Date());

        double newBalance = account.getAccountBalance() - amount;
        account.setAccountBalance(newBalance);

        // Save the updated account and transaction
        transactionDao.saveTransaction(transaction);
        transactionDao.updateAccount(account);
    }

    // Method to get the most recent withdrawal amount for a given account
    public Double getMostRecentWithdrawal(Account account) {
        return transactionDao.getMostRecentWithdrawal(account);
    }
}
