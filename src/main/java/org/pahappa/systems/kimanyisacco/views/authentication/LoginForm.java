package org.pahappa.systems.kimanyisacco.views.authentication;

import java.io.IOException;

import javax.faces.application.FacesMessage; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.navigation.Navigation;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.LoginServiceImpl;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm {
    private User user;
    private boolean loggedIn; // To keep track of the user's login status
    private LoginServiceImpl loginService;

    public LoginForm() {
        this.user = new User();
        this.loginService = new LoginServiceImpl();
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
        User loggedInUser = loginService.authenticateAndGetUser(user.getUsername(), user.getPassword());

        if (loggedInUser != null && "Approved".equals(loggedInUser.getStatus())) {
            loggedIn = true;
            setSessionAttributes(loggedInUser);
            if(loggedInUser.getUsername().equalsIgnoreCase("admin")){
                redirectToPage(Navigation.ADMIN);
            }
            redirectToPage(Navigation.DASHBOARD);
        } else {
            displayMessage();
            // redirectToPage(Navigation.LOGIN);
        }
    }

    public void displayMessage() {  
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage("growl", new FacesMessage("Error", "Invalid login credentials"));  
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
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        loggedIn = false; // Update the login status
        try {
            externalContext.redirect(request.getContextPath() + "/pages/authenticate/login.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
