package com.example.client.services;

import com.example.client.Pair;
import com.example.shared.Scenario;
import com.example.shared.Story;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("getStories")
public interface GetStoriesService extends RemoteService {
    List<Story> getStories();
    Story getStoryByName(String name);
    List<Story> getAllStoriesByCategoria(String categoria);
    List<Story> getAllStoriesByAutore(String autore);

    boolean removeStoryByName(Story story);

    boolean modifyStoryByName(Story story);

    boolean saveCurrentStory(String username, Story story, Scenario currentScenario);

    Pair<Scenario, Story> loadCurrentStory(String username, Story story);

}
