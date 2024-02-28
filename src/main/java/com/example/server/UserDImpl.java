package com.example.server;

import java.util.Iterator;
import com.example.shared.User;

// Implementazione dei metodi del DAO dell'utente, quindi cosa fanno metodi scritti in UserDAO
public class UserDImpl implements UserD {

    @Override
    public User getUser(String email, String password) {
        return Database.getInstance().checkLogin(email, password);
    }


    public User getUserByEmail(String email) {
        Iterator<User> itUser = Database.getInstance().getUsersSet().iterator();
        while (itUser.hasNext()) {
            User tmp = itUser.next();
            if (tmp.getEmail().equals(email)) return tmp;
        }
        return null;
    }

    @Override
    public boolean addUser(User newUser) {
        return Database.getInstance().addUser(newUser);
    }   
}