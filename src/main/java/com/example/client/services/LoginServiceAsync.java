package com.example.client.services;

import com.example.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

    void login(String username, String password, AsyncCallback<Boolean> callback);

    void setUserLogged(User user, AsyncCallback<Boolean> async);

    void getUserLogged(AsyncCallback<User> async);

    void logout(AsyncCallback<Void> callback);
}
