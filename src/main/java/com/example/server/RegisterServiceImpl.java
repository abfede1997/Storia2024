package com.example.server;

import com.example.client.services.RegisterService;
import com.example.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

    private UserD userDAO = new UserDImpl();

    @Override
    public boolean registerUser(String username, String password) {
        return this.userDAO.addUser(new User(username, password));
    }

    @Override
    public boolean setUserLogged(User user) {
        return this.userDAO.setUserLogged(user);
    }
}
