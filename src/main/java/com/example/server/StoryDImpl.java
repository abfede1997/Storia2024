package com.example.server;

import com.example.shared.Story;

import java.util.ArrayList;
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
    public List<Story> getAllStoriesByCategoria(String categoria) {
        // Implementazione per ottenere le storie in base alla categoria
        List<Story> stories = Database.getInstance().getStories();
        List<Story> storieCategoria = new ArrayList<>();
        for (Story s : stories) {
            if (s.getCategoria().equals(categoria)) {
                storieCategoria.add(s);
            }
        }
        return storieCategoria; 
    }

    @Override
    public List<Story> getAllStoriesByAutore(String autore) {
        // Implementazione per ottenere le storie in base all'autore
        List<Story> stories = Database.getInstance().getStories();
        List<Story> storieAutore = new ArrayList<>();
        for (Story s : stories) {
            if (s.getAutore().equals(autore)) {
                storieAutore.add(s);
            }
        }
        return storieAutore;
    }

    @Override
    public boolean removeStoryByName(Story story) {
        return Database.getInstance().removeStoryByName(story);
    }

}