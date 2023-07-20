package org.pahappa.systems.kimanyisacco.views.users;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.bean.ViewScoped;

import org.pahappa.systems.kimanyisacco.models.User;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public UserBean(){
        this.users = new ArrayList<>();
        for(int i = 0; i< 5;i++){
            User user = new User();
            user.setUsername("User"+i);
            user.setPassword("password"+i);
            users.add(user);

        }
    }
    public void deleteUser(User user){
        this.users.remove(user);
    }

    
}
