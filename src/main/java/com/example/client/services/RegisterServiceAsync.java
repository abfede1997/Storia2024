package com.example.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {

    void registerUser(String email, String password, AsyncCallback<Boolean> callback);

}
