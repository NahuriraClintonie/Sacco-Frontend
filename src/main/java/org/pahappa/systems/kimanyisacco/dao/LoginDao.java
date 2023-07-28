package org.pahappa.systems.kimanyisacco.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
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
}
