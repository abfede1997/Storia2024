package com.example.sweng.server.services;

import com.example.sweng.client.services.getStories.GetStoriesService;
import com.example.sweng.server.database.d.StoryD;
import com.example.sweng.server.database.d.StoryDImpl;
import com.example.sweng.shared.storia.Story;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

public class GetStoriesServiceImpl extends RemoteServiceServlet implements GetStoriesService {
    private StoryD storyDAO = new StoryDImpl();

    @Override
    public List<Story> getStories() {
        return storyDAO.getStories();
    }
}
