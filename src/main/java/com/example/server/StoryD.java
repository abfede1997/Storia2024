package com.example.server;

import com.example.client.Pair;
import com.example.shared.Scenario;
import com.example.shared.Story;

import java.util.List;

public interface StoryD {

    public boolean addStory(Story storyUser);

    public List<Story> getStories();

    public Story getStoryByName(String name);

    public List<Story> getAllStoriesByCategoria(String categoria);

    public List<Story> getAllStoriesByAutore(String autore);

    boolean removeStoryByName(Story story);

    boolean modifyStoryByName(Story story);

    boolean saveCurrentStory(String username, Story story, Scenario currentScenario);

    Pair<Scenario, Story> loadCurrentStory(String username, Story story);

}
