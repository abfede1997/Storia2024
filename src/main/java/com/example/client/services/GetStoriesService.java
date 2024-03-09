package com.example.sweng.client.services.getStories;

import com.example.sweng.shared.storia.Story;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("getStories")
public interface GetStoriesService extends RemoteService {
    List<Story> getStories();
}
