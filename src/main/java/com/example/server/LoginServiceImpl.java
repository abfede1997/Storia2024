package com.example.server;

import com.example.client.services.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    private UserD userD = new UserDImpl();

    @Override
    public boolean login(String email, String password) {
        return this.userD.getUser(email,password)!=null;
    }
}