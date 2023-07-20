 package org.pahappa.systems.kimanyisacco.views.users;

// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.cfg.Configuration;
// import javax.faces.bean.ManagedBean;
// // import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;

// // import org.pahappa.systems.kimanyisacco.config.SessionConfiguration;
// import org.pahappa.systems.kimanyisacco.models.Register;

// @ManagedBean()
// @ViewScoped
// public class RegisterBean {
//     private Register user;

//     public RegisterBean() {
//         user = new Register();
//     }

//     public Register getUser() {
//         return user;
//     }

//     public void setUser(Register user) {
//         this.user = user;
//     }

//     public void registerUser() {
//         try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//              Session session = sessionFactory.openSession()) {
//             session.beginTransaction();
//             session.save(user);
//             session.getTransaction().commit();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }



import org.pahappa.systems.kimanyisacco.models.Register;
import org.pahappa.systems.kimanyisacco.services.ServiceImpl.RegisterServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean {
    private Register register;
    RegisterServiceImpl registerService = new RegisterServiceImpl();

    // public RegisterBean(){

    // }

    public RegisterBean() {
        register = new Register();
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    
    public void registerUser() {
        registerService.registerUser(register);
    }
}