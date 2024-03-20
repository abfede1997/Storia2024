package com.example.server;

import com.example.client.services.LoginService;
import com.example.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    private UserD userD = new UserDImpl();

    @Override
    public boolean login(String username, String password) {
        return this.userD.getUser(username,password)!=null;
    }

    @Override
    public boolean setUserLogged(User user) {
        return this.userD.setUserLogged(user);
    }

    @Override
    public User getUserLogged() {
        return this.userD.getUserLogged();
    }
}