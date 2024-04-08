package com.example.server;

//import org

import com.example.client.Pair;
import com.example.shared.Scenario;
import com.example.shared.Story;
import com.example.shared.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.util.*;

public class Database {

    private static Database instance = null;
    private static DB fileDB;


    public Database(){
        fileDB = DBMaker.fileDB("../fileDB").transactionEnable().closeOnJvmShutdown().make();
    }

    public static Database getInstance() {
        instance = new Database();
        return instance;
    }


    public NavigableSet<User> getUsersSet() {
        NavigableSet<User> users = fileDB.treeSet("users", User.class).createOrOpen();
        fileDB.close();
        fileDB = null;
        return users;
    }

    public List<Story> getStories() {
        List<Story> stories = new ArrayList<>(fileDB.treeSet("story", Story.class).createOrOpen());
        fileDB.close();
        fileDB = null;
        return stories;
    }

    public User checkLogin(String username, String password) {
        NavigableSet<User> userSet = fileDB.treeSet("users", User.class).createOrOpen();
        User newUser = new User(username, password);
        boolean tmp = userSet.contains(newUser);
        fileDB.close();
        fileDB = null;
        if(tmp) return newUser;
        else return null;
    }

    public boolean addUser(User newUser) {
        NavigableSet<User> userSet = fileDB.treeSet("users", User.class).createOrOpen();
        for (User u : userSet) {
            if (u.getUsername().equals(newUser.getUsername())) {
                fileDB.close();
                fileDB = null;
                return false;
            }
        }
        userSet.add(newUser);
        fileDB.commit();
        fileDB.close();
        fileDB = null;
        return true;
    }

    public boolean addStory(Story newStory) {
        NavigableSet<Story> storySet = fileDB.treeSet("story", Story.class).createOrOpen();
        for (Story s : storySet) {
            if (s.getNome().equals(newStory.getNome())) {
                fileDB.close();
                fileDB = null;
                return false;
            }
        }
        storySet.add(newStory);
        fileDB.commit();
        fileDB.close();
        fileDB = null;
        return true;
    }

    public boolean setUserLogged(User user) {
        NavigableSet<User> userLoggedSet = fileDB.treeSet("userLogged", User.class).createOrOpen();
        userLoggedSet.clear();
        userLoggedSet.add(user);
        if(userLoggedSet.contains(user)){
            fileDB.commit();
            fileDB.close();
            fileDB = null;
            return true;
        }
        fileDB.close();
        fileDB = null;
        return false;
    }

    public User getUserLogged() {
        NavigableSet<User> userLoggedSet = fileDB.treeSet("userLogged", User.class).createOrOpen();
        User tmp = userLoggedSet.first();
        fileDB.close();
        fileDB = null;
        return tmp;
    }

    public boolean removeStoryByName(Story story) {
        NavigableSet<Story> stories = fileDB.treeSet("story", Story.class).createOrOpen();
        stories.remove(story);
        if(!stories.contains(story)){
            fileDB.commit();
            fileDB.close();
            fileDB = null;
            return true;
        }
        fileDB.close();
        fileDB = null;
        return false;
    }

    public boolean modifyStoryByName(Story story) {
        NavigableSet<Story> stories = fileDB.treeSet("story", Story.class).createOrOpen();
        Iterator<Story> it = stories.iterator();


        while(it.hasNext()) {
            Story tmp = it.next();
            if(tmp.getNome().equals(story.getNome())){
                tmp = story;
                fileDB.commit();
                fileDB.close();
                fileDB = null;
                return true;
            }
        }

        fileDB.close();
        fileDB = null;
        return false;
    }

    public boolean saveCurrentStory(String username, Story story, Scenario currentScenario) {
        HTreeMap<Scenario, Pair> savedScenariosStory = fileDB.hashMap("savedScenariosStory", Scenario.class, Pair.class).createOrOpen();

        boolean storyExists = false;
        Scenario scenarioTmp = null;
        String usernameTmp;
        Story storyTmp;


        Iterator<Map.Entry<Scenario, Pair>> it = savedScenariosStory.getEntries().iterator();
        while(it.hasNext()){
            Map.Entry<Scenario, Pair> tmp = it.next();

            scenarioTmp = tmp.getKey();
            usernameTmp = (String) tmp.getValue().getA();
            storyTmp = (Story) tmp.getValue().getB();

            if(usernameTmp.equals(username) && storyTmp.getNome().equals(story.getNome())){
                storyExists = true;
                break;
            }
        }

        if(storyExists) {
            savedScenariosStory.remove(scenarioTmp);
        }
        savedScenariosStory.put(currentScenario, new Pair<>(username, story));

        if(savedScenariosStory.containsKey(currentScenario) && savedScenariosStory.containsValue(new Pair<>(username, story))){
            fileDB.commit();
            fileDB.close();
            fileDB = null;
            return true;
        } else {
            fileDB.close();
            fileDB = null;
            return false;
        }
    }

    public Pair<Scenario, Story> loadCurrentStory(String username, Story story) {
        HTreeMap<Scenario, Pair> savedScenariosStory = fileDB.hashMap("savedScenariosStory", Scenario.class, Pair.class).createOrOpen();

        Pair<Scenario, Story> toReturn;

        Scenario scenarioTmp = null;
        String usernameTmp;
        Story storyTmp = null;

        Iterator<Map.Entry<Scenario, Pair>> it = savedScenariosStory.getEntries().iterator();
        while(it.hasNext()){
            Map.Entry<Scenario, Pair> tmp = it.next();

            scenarioTmp = tmp.getKey();
            usernameTmp = (String) tmp.getValue().getA();
            storyTmp = (Story) tmp.getValue().getB();

            if(usernameTmp.equals(username) && storyTmp.getNome().equals(story.getNome())){
                break;
            }
        }

        toReturn = new Pair<>(scenarioTmp, storyTmp);

        fileDB.close();
        fileDB = null;

        return toReturn;
    }
}
