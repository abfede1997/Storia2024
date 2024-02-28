package com.example.client.services;

import com.example.shared.Story;
import com.google.gwt.user.client.rpc.AsyncCallback;
 
public interface AddStoryServiceAsync {
    void addStory(Story newStory, AsyncCallback<Boolean> callback);
}
