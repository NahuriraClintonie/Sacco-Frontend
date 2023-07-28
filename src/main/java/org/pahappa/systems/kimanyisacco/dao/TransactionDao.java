package org.pahappa.systems.kimanyisacco.dao;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionDao {

    // Method to save the transaction details in the database
    public void saveTransaction(Transactions transaction) {
        Transaction dbTransaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            dbTransaction = session.beginTransaction();
            session.save(transaction);
            dbTransaction.commit();
        } catch (Exception e) {
            if (dbTransaction != null) {
                dbTransaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to update the account details in the database
    public void updateAccount(Account account) {
        Transaction dbTransaction = null;
        Session session = SessionConfiguration.getSessionFactory().openSession();
        try {
            dbTransaction = session.beginTransaction();
            session.update(account);
            dbTransaction.commit();
        } catch (Exception e) {
            if (dbTransaction != null) {
                dbTransaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to get the most recent withdrawal amount for a given account
    public Double getMostRecentWithdrawal(Account account) {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT t.amount FROM Transactions t WHERE t.account = :account "
                    + "AND t.transactionType = 'withdraw' ORDER BY t.date DESC");
            query.setParameter("account", account);
            query.setMaxResults(1);
            Double amount = (Double) query.uniqueResult();
            session.close();
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
