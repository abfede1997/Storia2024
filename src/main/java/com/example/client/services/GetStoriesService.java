package com.example.client.services;

import com.example.shared.Story;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("getStories")
public interface GetStoriesService extends RemoteService {
    List<Story> getStories();
}
