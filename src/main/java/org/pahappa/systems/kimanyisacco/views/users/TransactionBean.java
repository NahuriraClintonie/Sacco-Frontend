package org.pahappa.systems.kimanyisacco.views.users;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.AccountServiceImpl;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.RegisterServiceImpl;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.TransactionServiceImpl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="transaction")
@SessionScoped
public class TransactionBean {
    private double amount;
    private String transactionType;
    private AccountServiceImpl accountService;
    private TransactionServiceImpl transactionService;
    private Account userAccount; // The user's account fetched from the database
    public RegisterServiceImpl registerService;

    public TransactionBean() {
        registerService = new RegisterServiceImpl();
        accountService = new AccountServiceImpl();
        transactionService = new TransactionServiceImpl();
        fetchUserAccountFromSession();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAccountBalance() {
        if (userAccount != null) {
            return userAccount.getAccountBalance();
        }
        return 0.0;
    }

    public void saveTransaction() {
        if (userAccount != null) {
            if ("deposit".equalsIgnoreCase(transactionType)) {
                transactionService.saveDepositTransaction(userAccount, amount);
                Double mostRecentWithdrawal = transactionService.getMostRecentWithdrawal(userAccount);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("mostRecentWithdrawal", mostRecentWithdrawal);
                facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", "Deposit succesful"));
            } else if ("withdraw".equalsIgnoreCase(transactionType)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                if (userAccount.getAccountBalance() < amount) {
                    System.out.println(userAccount.getAccountBalance());
                    
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Insufficient account balance, please try with a lower amount"));
                } else {
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", "Successfully withdrawn "+amount+" from account number "+userAccount.getAccountNumber()));
                    transactionService.saveWithdrawTransaction(userAccount, amount);
                }
            }
        }
    }

    private void fetchUserAccountFromSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            userAccount = accountService.getAccountByRegister(loggedInUser);
            System.out.println(userAccount);
        }
    }

    public List<User> getAllMembers() {
        List<User> allMembers = null;
        if (allMembers == null) {
            allMembers = registerService.getAllAccounts(); // Use getAllAccounts() instead of getAllMembers()
        }
        return allMembers;
    }
}
