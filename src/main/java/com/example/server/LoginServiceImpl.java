package com.example.server;

import javax.servlet.http.HttpSession;

import com.example.client.services.LoginService;
import com.example.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

    @Override
    public void logout() {
        HttpSession session = getThreadLocalRequest().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}