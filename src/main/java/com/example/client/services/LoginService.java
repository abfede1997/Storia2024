package com.example.client.services;

import com.example.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

    boolean login(String username, String password);

    boolean setUserLogged(User user);

    User getUserLogged();
}
