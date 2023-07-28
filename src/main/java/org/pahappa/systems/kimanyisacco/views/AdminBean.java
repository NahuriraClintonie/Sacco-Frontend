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
    private List<User> allMembers;
    private Long totalApprovedUsers;
    private Long totalPendingUsers;
    private Long totalMembers;
    private Long totalWithdrawTransactions;
    private Long totalDepositTransactions;
    

    public AdminBean() {
        registerService = new RegisterServiceImpl();
    }

    public List<User> getAllAccounts() {
        if (allMembers == null) {
            allMembers = registerService.getAllAccounts();
        }
        return allMembers;
    }

    

    public void approveUser(User user) {
        user.setStatus("Approved");
        registerService.updateUser(user);
    }

    public void rejectUser(User user) {
        registerService.deleteUser(user);
        allMembers.remove(user);
    }

    public Long getTotalApprovedUsers() {
        if (totalApprovedUsers == null) {
            totalApprovedUsers = registerService.getTotalApprovedUsers();
        }
        System.out.println(totalApprovedUsers);
        return totalApprovedUsers;
    }

    public Long getTotalPendingUsers() {
        if (totalPendingUsers == null) {
            totalPendingUsers = registerService.getTotalPendingUsers();
        }
        return totalPendingUsers;
    }

    public Long getTotalMembers() {
        if (totalMembers == null) {
            totalMembers = registerService.getTotalMembers();
        }
        return totalMembers;
    }

    public Long getTotalWithdrawTransactions() {
        if (totalWithdrawTransactions == null) {
            totalWithdrawTransactions = registerService.getTotalWithdrawTransactions();
        }
        return totalWithdrawTransactions;
    }

    public Long getTotalDepositTransactions() {
        if (totalDepositTransactions == null) {
            totalDepositTransactions = registerService.getTotalDepositTransactions();
        }
        return totalDepositTransactions;
    }
}
