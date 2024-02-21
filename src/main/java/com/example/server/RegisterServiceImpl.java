package com.example.server;

import com.example.client.services.RegisterService;
import com.example.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

    private UserD userDAO = new UserDImpl();

    @Override
    public boolean registerUser(String email, String password) {
        return this.userDAO.addUser(new User(email, password));
    }
}
