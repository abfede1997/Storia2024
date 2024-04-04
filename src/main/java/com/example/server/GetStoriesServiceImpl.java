package com.example.server;

import com.example.client.Pair;
import com.example.client.services.GetStoriesService;
import com.example.shared.Scenario;
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
    @Override
    public List<Story> getAllStoriesByCategoria(String categoria){
        return storyDAO.getAllStoriesByCategoria(categoria);
    }
    @Override
    public List<Story> getAllStoriesByAutore(String autore){
        return storyDAO.getAllStoriesByAutore(autore);
    }

    @Override
    public boolean removeStoryByName(Story story) {
        return storyDAO.removeStoryByName(story);
    }

    @Override
    public boolean modifyStoryByName(Story story) {
        return storyDAO.modifyStoryByName(story);
    }

    @Override
    public boolean saveCurrentStory(String username, Story story, Scenario currentScenario) {
        return storyDAO.saveCurrentStory(username, story, currentScenario);
    }

    @Override
    public Pair<Scenario, Story> loadCurrentStory(String username, Story story) {
        return storyDAO.loadCurrentStory(username, story);
    }

}
