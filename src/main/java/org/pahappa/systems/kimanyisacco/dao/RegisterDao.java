package org.pahappa.systems.kimanyisacco.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.User;

public class RegisterDao {

    public User registerUser(User user) {
        Transaction transaction = null;
        try {

            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Update the user details in the database
            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void rejectUser(User user) {
        Transaction transaction = null;

        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllMembers() {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            return session.createQuery("SELECT a FROM Account a LEFT JOIN FETCH a.user u WHERE u.username <> 'admin'").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Long getTotalApprovedUsers() {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.status = 'Approved' AND u.username <> 'admin'");
            Long result = (Long)query.uniqueResult();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long getTotalPendingUsers() {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.status = 'pending' AND u.username <> 'admin'");
            Long result = (Long) query.uniqueResult();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long getTotalRejectedUsers() {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.status = 'Rejected' AND u.username <> 'admin'");
            Long result = (Long) query.uniqueResult();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //     

    public Long getTotalMembers() {
        try {
                Session session = SessionConfiguration.getSessionFactory().openSession();
                Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.username <> 'admin'");
                Long result = (Long) query.uniqueResult();
                session.close();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
    }
    
} 

public Long getTotalWithdrawTransactions() {
    try {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT COUNT(t) FROM Transactions t WHERE t.transactionType = 'Withdraw'");
        Long result = (Long) query.uniqueResult();
        session.close();
        return result;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public Long getTotalDepositTransactions() {
    try {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT COUNT(t) FROM Transactions t WHERE t.transactionType = 'Deposit'");
        Long result = (Long) query.uniqueResult();
        session.close();
        return result;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public List<User> getAllMembers1() {
    try {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        String query = "SELECT DISTINCT a FROM Account a "
                + "LEFT JOIN FETCH a.transactions "
                + "LEFT JOIN FETCH a.user "
                + "WHERE a.user.username <> 'admin'";
        return session.createQuery(query).list();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
