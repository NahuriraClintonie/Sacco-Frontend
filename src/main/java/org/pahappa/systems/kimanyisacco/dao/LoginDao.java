package org.pahappa.systems.kimanyisacco.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.models.User;

public class LoginDao {
    private static Session session;

    static {
        try {
            session = SessionConfiguration.getSessionFactory().openSession();
        } catch (Throwable ex) {
            
            ex.printStackTrace();
        }
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = (User) session.createQuery("FROM User WHERE username = :username AND password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 

        return null;
    }

    public User findUserByUsername(String username) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = (User) session.createQuery("FROM User WHERE username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    public Account getAccountByUserId(long user_id){
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Account account = (Account) session.createQuery("FROM Account WHERE user_id = :user_id")
                    .setParameter("user_id", user_id)
                    .uniqueResult();
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    public List<Transactions> getTransactionsByAccountNumber(String accountNumber) {
        Transaction transaction = null;
    
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Transactions WHERE account.accountNumber = :accountNumber");
            query.setParameter("accountNumber", accountNumber);
            List<Transactions> transactions = query.list();
            transaction.commit();
            return transactions;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    
        return null;
    }
    
public int getTotalTransactionsByAccountNumber(String accountNumber) {
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT COUNT(t) FROM Transactions t WHERE t.account.accountNumber = :accountNumber");
        query.setParameter("accountNumber", accountNumber);
        int totalTransactions = ((Number) query.uniqueResult()).intValue();
        transaction.commit();
        return totalTransactions;
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

    return 0;
}

public Double getMostRecentWithdrawAmountByAccountNumber(String accountNumber) {
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT t.amount FROM Transactions t WHERE t.account.accountNumber = :accountNumber AND t.transactionType = 'Withdraw' ORDER BY t.date DESC");
        query.setParameter("accountNumber", accountNumber);
        query.setMaxResults(1);
        Double withdrawAmount = (Double) query.uniqueResult();
        transaction.commit();
        return withdrawAmount;
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

    return null;
}

public Double getMostRecentDepositAmountByAccountNumber(String accountNumber) {
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT t.amount FROM Transactions t WHERE t.account.accountNumber = :accountNumber AND t.transactionType = 'deposit' ORDER BY t.date DESC");
        query.setParameter("accountNumber", accountNumber);
        query.setMaxResults(1);
        Double depositAmount = (Double) query.uniqueResult();
        transaction.commit();
        return depositAmount;
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

    return null;
}



}
