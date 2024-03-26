package com.example.server;

import com.example.client.services.GetStoriesService;
import com.example.shared.Story;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

public class GetStoriesServiceImpl extends RemoteServiceServlet implements GetStoriesService {
    private StoryD storyDAO = new StoryDImpl();

    @Override
    public List<Story> getStories() {
        return storyDAO.getStories();
    }
    @Override
    public Story getStoryByName(String name) {
        return storyDAO.getStoryByName(name);
    }
}
