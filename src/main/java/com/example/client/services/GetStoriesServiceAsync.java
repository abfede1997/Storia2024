package com.example.sweng.client.services.getStories;

import com.example.sweng.shared.storia.Story;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface GetStoriesServiceAsync {
    void getStories(AsyncCallback<List<Story>> async);
}
