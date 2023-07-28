package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.dao.LoginDao;
import org.pahappa.systems.kimanyisacco.models.User;

public class LoginServiceImpl {
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
}