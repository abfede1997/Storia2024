package com.example.server;

import com.example.shared.Story;

import java.util.List;

public interface StoryD {

    public boolean addStory(Story storyUser);

    public List<Story> getStories();

  

}
