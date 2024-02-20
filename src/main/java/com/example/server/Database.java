package com.example.server;

import com.example.shared.Story;
import com.example.shared.User;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.NavigableSet;

public class Database {

    private static Database instance;
    private static DB fileDB;


    private Database(){
        fileDB = DBMaker.fileDB("./fileDB").closeOnJvmShutdown().make();
    }

    public static Database getInstance(){
        if (instance==null) {
            synchronized(Database.class) {
                if (instance==null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }


    public NavigableSet<User> getUsersSet() {
        return fileDB.treeSet("users", User.class).createOrOpen();
    }

    public NavigableSet<Story> getStorySet() {
        return fileDB.treeSet("story", Story.class).createOrOpen();
    }

}

