package com.example.client.services;

import com.example.shared.Story;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface GetStoriesServiceAsync {
    void getStories(AsyncCallback<List<Story>> async);
    void getStoryByName(String name, AsyncCallback<Story> callback);
    void getAllStoriesByCategoria(String categoria, AsyncCallback<List<Story>> callback);
    void getAllStoriesByAutore(String autore, AsyncCallback<List<Story>> callback);
}
