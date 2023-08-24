package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import java.util.List;

import org.pahappa.systems.kimanyisacco.dao.LoginDao;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.LoginService;

public class LoginServiceImpl implements LoginService{
    LoginDao loginDao = new LoginDao();

    public boolean authenticate(String username, String password) {   
        User foundUser = loginDao.findUserByUsernameAndPassword(username, password);    
        return foundUser != null;
    }

    public User authenticateAndGetUser(String username, String password) {
        if (authenticate(username, password)) {
            return loginDao.findUserByUsername(username);
        }
        return null;
    }

    public Account getAccountByUserId(long userId){
        return loginDao.getAccountByUserId(userId);
    }

    public List<Transactions> getTransactionsByAccountNumber(String accountNumber) {
        return loginDao.getTransactionsByAccountNumber(accountNumber);
    }

    public int getTotalTransactionsByAccountNumber(String accountNumber) {
        return loginDao.getTotalTransactionsByAccountNumber(accountNumber);
    }

    public Double getMostRecentWithdrawAmountByAccountNumber(String accountNumber) {
        return loginDao.getMostRecentWithdrawAmountByAccountNumber(accountNumber);
    }

    public Double getMostRecentDepositAmountByAccountNumber(String accountNumber) {
        return loginDao.getMostRecentDepositAmountByAccountNumber(accountNumber);
    }

}