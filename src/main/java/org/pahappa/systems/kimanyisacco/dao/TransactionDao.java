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

    public void updateAccountBalanceAfterDeposit(Long userId, double amount) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Query to find the corresponding Account based on the user_id
            String hql = "FROM Account WHERE user.id = :userId";
            Account account = (Account) session.createQuery(hql)
                    .setParameter("userId", userId)
                    .uniqueResult();

            if (account != null) {
                // Get the current account balance
                double currentBalance = account.getAccountBalance();

                // Update the account balance by adding the deposit amount
                double updatedBalance = currentBalance + amount;
                account.setAccountBalance(updatedBalance);

                // Save the updated account back to the database
                session.update(account);
            } else {
                // Handle the case when the account does not exist for the given user_id
                // For example, throw an exception or log an error message
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateAccountBalanceAfterWithdraw(Long userId, double withdrawAmount) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Query to find the corresponding Account based on the user_id
            String hql = "FROM Account WHERE user.id = :userId";
            Account account = (Account) session.createQuery(hql)
                    .setParameter("userId", userId)
                    .uniqueResult();

            if (account != null) {
                // Get the current account balance
                double currentBalance = account.getAccountBalance();

                // Check if the withdrawal amount is valid (not negative and less than the current balance)
                if (withdrawAmount >= 0 && withdrawAmount <= currentBalance) {
                    // Update the account balance by subtracting the withdrawal amount
                    double updatedBalance = currentBalance - withdrawAmount;
                    account.setAccountBalance(updatedBalance);

                    // Save the updated account back to the database
                    session.update(account);
                } else {
                    // Handle the case when the withdrawal amount is invalid
                    // For example, throw an exception or log an error message
                }
            } else {
                // Handle the case when the account does not exist for the given user_id
                // For example, throw an exception or log an error message
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Double getAccountBalanceByUserId(Long userId) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Query to find the corresponding Account based on the user_id
            String hql = "FROM Account WHERE user.id = :userId";
            Account account = (Account) session.createQuery(hql)
                    .setParameter("userId", userId)
                    .uniqueResult();

            if (account != null) {
                // Retrieve the account balance
                Double accountBalance = account.getAccountBalance();
                return accountBalance;
            } else {
                // Handle the case when the account does not exist for the given user_id
                // For example, throw an exception or log an error message
                return null;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    
}
