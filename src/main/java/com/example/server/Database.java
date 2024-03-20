package com.example.server;

//import org

import com.example.shared.Story;
import com.example.shared.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

public class Database {

    private static Database instance = null;
    private static DB fileDB;


    private Database(){
        fileDB = DBMaker.fileDB("../fileDB").transactionEnable().closeOnJvmShutdown().make();
    }

    public static Database getInstance() {
//        if (instance == null) {
//            synchronized(Database.class) {
//                if (instance==null) {
        instance = new Database();
//                }
//            }
//        }
        return instance;
    }


    public NavigableSet<User> getUsersSet() {
        return fileDB.treeSet("users", User.class).createOrOpen();
    }

    public List<Story> getStories() {
        List<Story> stories = new ArrayList<>(fileDB.treeSet("story", Story.class).createOrOpen());
        fileDB.close();
        fileDB = null;
        return stories;
    }

    public User checkLogin(String email, String password) {
        NavigableSet<User> userSet = fileDB.treeSet("users", User.class).createOrOpen();
        User newUser = new User(email, password);
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
}
