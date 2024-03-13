package com.example.server;

import java.util.List;

import com.example.shared.Story;

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