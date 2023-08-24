package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import java.util.List;

import org.pahappa.systems.kimanyisacco.constants.Status;
import org.pahappa.systems.kimanyisacco.dao.RegisterDao;
import org.pahappa.systems.kimanyisacco.models.User;

// import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.RegisterService;

public class RegisterServiceImpl implements RegisterService{
    private final RegisterDao registerDao = new RegisterDao();

    public User registerUser(User user){
        user.setStatus(Status.PENDING.toString());
        // String hashedPassword = hashPassword(register.getPassword());
        // register.setPassword(hashedPassword);
        return registerDao.registerUser(user);
    }

    public void updateUser(User user) {
        user.setStatus(Status.APPROVED.toString());
        registerDao.updateUser(user);
    }

    public void rejectUser(User user) {
        user.setStatus(Status.REJECTED.toString());
        registerDao.rejectUser(user);
    }

    public List<User> getAllAccounts() {
        return registerDao.getAllMembers();
        
    }

    public Long getTotalApprovedUsers() {
        
        return registerDao.getTotalApprovedUsers();
    }


    public Long getTotalPendingUsers() {
    
        return registerDao.getTotalPendingUsers();
    }

    public Long getTotalRejectedUsers() {
    
        return registerDao.getTotalRejectedUsers();
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
    return registerDao.getAllMembers(); 
}
}
