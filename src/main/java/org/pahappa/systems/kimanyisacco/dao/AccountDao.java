package org.pahappa.systems.kimanyisacco.dao;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDao {

    // Method to save the account details in the database
    public Account saveAccount(Account account) {
        Transaction transaction = null;
        try  {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    

    // Method to fetch the Account associated with a Register object
    public Account getAccountByRegister(User registeredUser) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        try  {
            Account account = (Account)session.createQuery("FROM Account WHERE user = :user").setParameter("user", registeredUser).uniqueResult();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
