package org.pahappa.systems.kimanyisacco.views.authentication;

import java.io.IOException;

// import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.LoginServiceImpl;

@ManagedBean(name="loginForm")
@SessionScoped
public class LoginForm {
    private User user;

    public LoginForm(){
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

     public void login() {
        LoginServiceImpl loginService = new LoginServiceImpl();
        if (loginService.authenticate(user.getUsername(), user.getPassword())) {
            // System.out.println(user.getUsername());
            // System.out.println(user.getPassword());
            // System.out.println("No connection occured");
            // Successful login, redirect to the dashboard
            redirectToPage(Hyperlinks.dashboard);
        } else {
            // Unsuccessful login, redirect back to the login page
            // System.out.println(user.getUsername());
            // System.out.println(user.getPassword());
            redirectToPage(Hyperlinks.login);
        }
    }

    private void redirectToPage(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String requestContext = externalContext.getRequestContextPath();
            System.out.println(requestContext + page);
            externalContext.redirect(requestContext + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
