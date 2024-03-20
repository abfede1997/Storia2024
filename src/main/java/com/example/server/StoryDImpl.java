package com.example.server;

import com.example.shared.Story;

import java.util.List;

public class StoryDImpl implements StoryD {


    @Override
    public boolean addStory(Story newStory) {
        return Database.getInstance().addStory(newStory);
    }

    @Override
    public List<Story> getStories() {
        // L'insieme di stories qui è un Array List, non un SET di MapDB
        return Database.getInstance().getStories();
    }

}