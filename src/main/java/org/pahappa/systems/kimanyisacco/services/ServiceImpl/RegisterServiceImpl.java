package org.pahappa.systems.kimanyisacco.services.ServiceImpl;

import org.pahappa.systems.kimanyisacco.dao.RegisterDao;
import org.pahappa.systems.kimanyisacco.models.Register;

// import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.RegisterService;

public class RegisterServiceImpl implements RegisterService{
    private final RegisterDao registerDao = new RegisterDao();

    public void registerUser(Register register){
        registerDao.registerUser(register);
    }
}
