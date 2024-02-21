package com.example.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

    void login(String email, String password, AsyncCallback<Boolean> callback);

}
