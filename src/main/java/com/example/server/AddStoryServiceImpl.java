package com.example.server;

import com.example.client.services.AddStoryService;
import com.example.shared.Story;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
 
public class AddStoryServiceImpl extends RemoteServiceServlet implements AddStoryService {
 
    private StoryD storyDAO = new StoryDImpl();
 
    @Override
    public boolean addStory(Story newStory) {
        return this.storyDAO.addStory(newStory);
    }
}
