package com.example.client.services;

import com.example.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {

    void registerUser(String username, String password, AsyncCallback<Boolean> callback);

    void setUserLogged(User user, AsyncCallback<Boolean> async);
}
