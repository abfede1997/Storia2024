package com.example.client.services;

import com.example.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService{

    boolean registerUser(String username, String password);

    boolean setUserLogged(User user);
}
