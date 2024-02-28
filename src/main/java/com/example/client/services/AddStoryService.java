package com.example.client.services;

import com.example.shared.Story;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
 
@RemoteServiceRelativePath("addStory")
public interface AddStoryService extends RemoteService {
    boolean addStory(Story newStory);
}
