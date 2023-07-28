package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.dao.RegisterDao;
import org.pahappa.systems.kimanyisacco.models.User;

// import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.RegisterService;

public class RegisterServiceImpl implements RegisterService{
    private List<User> AllAccounts;
    private final RegisterDao registerDao = new RegisterDao();

    public User registerUser(User register){
        register.setStatus("pending");
        // String hashedPassword = hashPassword(register.getPassword());
        // register.setPassword(hashedPassword);
        return registerDao.registerUser(register);
    }

    public void updateUser(User user) {
        registerDao.updateUser(user);
    }

    public void deleteUser(User user) {
        registerDao.deleteUser(user);
    }

    public List<User> getAllAccounts() {
        AllAccounts = registerDao.getAllMembers();
        return AllAccounts;
    }

    public Long getTotalApprovedUsers() {
        
        return registerDao.getTotalApprovedUsers();
    }


public Long getTotalPendingUsers() {
    
        return registerDao.getTotalPendingUsers();
    }

public Long getTotalMembers() {
    return registerDao.getTotalMembers();
}

public Long getTotalWithdrawTransactions() {
    return registerDao.getTotalWithdrawTransactions();
}

public Long getTotalDepositTransactions() {
    return registerDao.getTotalDepositTransactions();
}

// private String hashPassword(String Password){
//     return BCrypt.hashpw(Password,BCrypt.gensalt());
// }

public List<User> getAllAccounts1() {
    AllAccounts = registerDao.getAllMembers(); // Change to getAllMembers()
    return AllAccounts;
}
}
