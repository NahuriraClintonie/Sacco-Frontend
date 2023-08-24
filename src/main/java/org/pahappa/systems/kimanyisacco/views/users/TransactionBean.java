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
    public User loggedInUser;
    private Double balance;

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
            return userAccount.getAccountBalance();   
    }

    public Double getBalance() {
        System.out.println(loggedInUser.getId());
            return transactionService.getAccountBalanceByUserId(loggedInUser.getId());   
    }

   
    public void saveTransaction() {
        Account account1 = fetchUserAccountFromSession();
        if (account1 != null) {
            if ("deposit".equalsIgnoreCase(transactionType)) {
                if(amount < 500.0){
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Invalid amount, the minimum deposit amount is Shs. 500.0"));
                } else{
                    
                   transactionService.saveDepositTransaction(account1, amount);
                   transactionService.updateAccountBalance(loggedInUser.getId(), amount);
                   FacesContext facesContext = FacesContext.getCurrentInstance();
                   facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", "Deposit succesful"));
                }
                
            } else if ("withdraw".equalsIgnoreCase(transactionType)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                
                
                if (getBalance() < amount) {
                    
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Insufficient account balance, please try with a lower amount"));
                } 
                else if((amount < 500.0)){
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR", "Invalid amount, the minimum withdraw amount is Shs. 500.0"));
                }
                else {
                    facesContext.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", "Successfully withdrawn "+amount+" from your account"));
                    transactionService.saveWithdrawTransaction(account1, amount);
                    transactionService.updateAccountBalanceAfterWithdraw(loggedInUser.getId(),amount);
                }
            }
        }
    }

    private Account fetchUserAccountFromSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return accountService.getAccountByRegister(loggedInUser);
        }
        return null;
    }

    public List<User> getAllMembers() {
        List<User> allMembers = null;
        if (allMembers == null) {
            allMembers = registerService.getAllAccounts(); // Use getAllAccounts() instead of getAllMembers()
        }
        return allMembers;
    }
}
