package org.pahappa.systems.kimanyisacco.views;


import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.RegisterServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean {
    private RegisterServiceImpl registerService;
    

    public AdminBean() {
        registerService = new RegisterServiceImpl();
    }

    public List<User> getAllAccounts() {
        return registerService.getAllAccounts();
    }

    

    public void approveUser(User user) {
        registerService.updateUser(user);
    }

    public void rejectUser(User user) {
        registerService.rejectUser(user);
    }

    public Long getTotalApprovedUsers() {
        // if (totalApprovedUsers == null) {
        //     totalApprovedUsers = registerService.getTotalApprovedUsers();
        //     return totalApprovedUsers;
        // }
        // System.out.println(totalApprovedUsers);
        return registerService.getTotalApprovedUsers();
    }

    public Long getTotalPendingUsers() {
        // if (totalPendingUsers == null) {
        //     totalPendingUsers = registerService.getTotalPendingUsers();
        //     // return totalPendingUsers;
        // }
        return registerService.getTotalPendingUsers();
    }

    public Long getTotalRejectedUsers() {
        // if (totalPendingUsers == null) {
        //     totalPendingUsers = registerService.getTotalPendingUsers();
        //     // return totalPendingUsers;
        // }
        return registerService.getTotalRejectedUsers();
    }

    public Long getTotalMembers() {
        // if (totalMembers == null) {
        //     totalMembers = registerService.getTotalMembers();
        // }
        return registerService.getTotalMembers();
    }

    public Long getTotalWithdrawTransactions() {
        // if (totalWithdrawTransactions == null) {
        //     totalWithdrawTransactions = registerService.getTotalWithdrawTransactions();
        // }
        return registerService.getTotalWithdrawTransactions();
    }

    public Long getTotalDepositTransactions() {
        // if (totalDepositTransactions == null) {
        //     totalDepositTransactions = registerService.getTotalDepositTransactions();
        // }
        return registerService.getTotalDepositTransactions();
    }

    
}
