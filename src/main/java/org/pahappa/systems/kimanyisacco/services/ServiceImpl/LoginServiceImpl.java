package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.dao.LoginDao;
import org.pahappa.systems.kimanyisacco.models.Register;

public class LoginServiceImpl {
    public boolean authenticate(String username, String password) {
        LoginDao loginDao = new LoginDao();
        Register foundUser = loginDao.findUserByUsernameAndPassword(username, password);
        // if(!foundUser.equals(null)){
        //     System.out.println("The user was actually found");
        //     return true;
        // }else{
        //     System.out.println("The user was actually not found");
        //     return false;
        // }
        return foundUser != null;
    }
}