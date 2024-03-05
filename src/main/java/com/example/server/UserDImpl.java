package com.example.server;

import java.util.Iterator;
import com.example.shared.User;

// Implementazione dei metodi del DAO dell'utente, quindi cosa fanno metodi scritti in UserDAO
public class UserDImpl implements UserD {

    @Override
    public User getUser(String username, String password) {
        return Database.getInstance().checkLogin(username, password);
    }


    public User getUserByUsername(String username) {
        Iterator<User> itUser = Database.getInstance().getUsersSet().iterator();
        while (itUser.hasNext()) {
            User tmp = itUser.next();
            if (tmp.getUsername().equals(username)) return tmp;
        }
        return null;
    }

    @Override
    public boolean addUser(User newUser) {
        return Database.getInstance().addUser(newUser);
    }   
}