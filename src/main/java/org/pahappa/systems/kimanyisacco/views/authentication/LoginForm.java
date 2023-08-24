package org.pahappa.systems.kimanyisacco.views.authentication;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transactions;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.navigation.Navigation;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.LoginServiceImpl;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm {
    private User user;
    private User loggedInUser;
    private boolean loggedIn; // To keep track of the user's login status
    private LoginServiceImpl loginService;
    private Account account;
    private long userId;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LoginForm() {
        this.user = new User();
        this.loginService = new LoginServiceImpl();
        loggedInUser = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        if(user.getUsername().equalsIgnoreCase("admin")){
            redirectToPage(Navigation.ADMIN);
        } else{
            loggedInUser = loginService.authenticateAndGetUser(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            loggedIn = true;
            setSessionAttributes(loggedInUser);
            userId = loggedInUser.getId();
            redirectToPage(Navigation.DASHBOARD);
        } else {
            displayMessage();
            // redirectToPage(Navigation.LOGIN);
        }
    }
        
    }

    //getting logged in user's account
    
    public Account getAccountByUserId(long userId){
        return loginService.getAccountByUserId(userId);
    }

    //String account_number = getAccountByUserId(userId).getAccountNumber();

    public List<Transactions> getTransactionsForLoggedInUser() {
        
        return loginService.getTransactionsByAccountNumber(getAccountByUserId(userId).getAccountNumber());
    }

    public int getTotalTransactionsByAccountNumber() {
        return loginService.getTotalTransactionsByAccountNumber(getAccountByUserId(userId).getAccountNumber());
    }

    public Double getMostRecentWithdrawAmountByAccountNumber() {
        return loginService.getMostRecentWithdrawAmountByAccountNumber(getAccountByUserId(userId).getAccountNumber());
    }

    public Double getMostRecentDepositAmountByAccountNumber() {
        return loginService.getMostRecentDepositAmountByAccountNumber(getAccountByUserId(userId).getAccountNumber());
    }

    public void displayMessage() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Invalid login credentials"));
        // context.addMessage("growl", new FacesMessage("Error", "Invalid login credentials"));  
        }  

    public void redirectToPage(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String requestContext = externalContext.getRequestContextPath();
            externalContext.redirect(requestContext + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSessionAttributes(User loggedInUser) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession(true);

        // Store user information in the session
        session.setAttribute("loggedInUser", loggedInUser);
    }

    public void logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession(false); // Get the existing session, if any
    
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
    
        try {
            redirectToPage(Navigation.LOGIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
