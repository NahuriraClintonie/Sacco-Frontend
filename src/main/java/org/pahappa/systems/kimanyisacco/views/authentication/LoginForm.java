package org.pahappa.systems.kimanyisacco.views.authentication;

// import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;

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
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String requestContext = externalContext.getRequestContextPath();

        try {
            // System.out.println(requestContext);
            // System.out.println(Hyperlinks.dashboard);
            externalContext.redirect(requestContext+Hyperlinks.dashboard);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void DoLogin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String requestContext = externalContext.getRequestContextPath();

        try {
            // System.out.println(requestContext);
            // System.out.println(Hyperlinks.dashboard);
            externalContext.redirect(requestContext+Hyperlinks.login);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
