package com.example.server;

import com.example.shared.Story;
import com.example.shared.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.NavigableSet;

public class Database {

    private static Database instance = null;
    private static DB fileDB;


    private Database(){
        fileDB = DBMaker.fileDB("./fileDB").transactionEnable().closeOnJvmShutdown().make();
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

    public NavigableSet<Story> getStorySet() {
        return fileDB.treeSet("story", Story.class).createOrOpen();
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
            if (u.getEmail().equals(newUser.getEmail())) {
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

}

