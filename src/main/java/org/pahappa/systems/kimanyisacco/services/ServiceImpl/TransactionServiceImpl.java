package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.services.TransactionService;
import org.pahappa.systems.kimanyisacco.dao.TransactionDao;

import java.util.Date;

public class TransactionServiceImpl implements TransactionService{
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
        transactionDao.saveTransaction(transaction);
    }

    public void saveWithdrawTransaction(Account account, double amount) {
        Transactions transaction = new Transactions();
        transaction.setAccount(account);
        transaction.setTransactionType("Withdraw");
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transactionDao.saveTransaction(transaction);
    }

    
    public Double getMostRecentWithdrawal(Account account) {
        return transactionDao.getMostRecentWithdrawal(account);
    }

    public void updateAccountBalance(Long userId, double amount) {
        transactionDao.updateAccountBalanceAfterDeposit(userId,amount);
    }

    public void updateAccountBalanceAfterWithdraw(Long userId, double amount) {
        transactionDao.updateAccountBalanceAfterWithdraw(userId,amount);
    }

    public Double getAccountBalanceByUserId(Long userId) {
        return transactionDao.getAccountBalanceByUserId(userId);
    }
}
