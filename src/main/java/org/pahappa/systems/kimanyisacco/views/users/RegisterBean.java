package org.pahappa.systems.kimanyisacco.views.users;

import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.navigation.Navigation;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.AccountServiceImpl;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.RegisterServiceImpl;
import org.pahappa.systems.kimanyisacco.views.authentication.LoginForm;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean {
    private User user;
    RegisterServiceImpl registerService;
    AccountServiceImpl accountService;
    LoginForm loginf;
 

    public RegisterBean() {
        user = new User();
        registerService = new RegisterServiceImpl();
        accountService = new AccountServiceImpl();
        loginf = new LoginForm();
    }
    
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void registerUser() {
        //user.setGender(gender);
        User registeredUser = registerService.registerUser(user);
        if (registeredUser != null) {
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage("registermessage", new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", "Successfully registered, wait for approval to login"));  
            Account account = accountService.createAccountForUser(registeredUser);
            if (account != null) {
                System.out.println("Account created and user registered successfully");
                loginf.redirectToPage(Navigation.LOGIN);
            } else {
                // Handle the case where the account creation failed
                System.out.println("Account creation failed");
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage("registermessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registration Failed", "Username already exists"));
            }
        
    }

    // public Double getAccountBalanceByUserId(){
    //     return registerService.getAccountBalanceByUserId
    // }

    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        if (email != null && !email.isEmpty()) {
            // You can add your custom email validation logic here
            if (!isValidEmail(email)) {
                //FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Email format", "Please enter a valid email address of the format example@gmail.com"));
            
                // FacesMessage message = new FacesMessage("Error","Invalid Email format. Please enter a valid email address of the format example@gmail.com");
                // throw new ValidatorException(message);
            }
        }
    }

        private boolean isValidEmail(String email) {
            return email.contains("@") && email.contains(".");
        }

}
