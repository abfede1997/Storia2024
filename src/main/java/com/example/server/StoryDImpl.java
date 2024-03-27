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

    @Override
    public Story getStoryByName(String name) {
        // Implementazione per ottenere una storia in base al nome
        List<Story> stories = Database.getInstance().getStories();
        for (Story s : stories) {
            if (s.getNome().equals(name)) {
                return s;
            }
        }
        return null; // Se non viene trovata nessuna storia con il nome specificato
    }

    @Override
    public Story getStoryByCategoria(String categoria) {
        // Implementazione per ottenere una storia in base alla categoria
        List<Story> stories = Database.getInstance().getStories();
        for (Story s : stories) {
            if (s.getCategoria().equals(categoria)) {
                return s;
            }
        }
        return null; // Se non viene trovata nessuna storia con la categoria specificata
    }

}