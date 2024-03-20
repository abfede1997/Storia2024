package com.example.server;

import com.example.shared.User;

import java.util.Iterator;

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
            if (tmp.getUsername().equals(email)) return tmp;
        }
        return null;
    }

    @Override
    public boolean setUserLogged(User user) {
        return Database.getInstance().setUserLogged(user);
    }

    @Override
    public User getUserLogged() {
        return Database.getInstance().getUserLogged();
    }

    @Override
    public boolean addUser(User newUser) {
        return Database.getInstance().addUser(newUser);
    }
}