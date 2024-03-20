package com.example.server;

import com.example.shared.User;

// Qui i metodi sono solo menzionati, sono implementati in UserDAOImpl
public interface UserD {

    public User getUser(String email, String password);

    public boolean addUser(User newUser);

    public User getUserByEmail(String email);

    boolean setUserLogged(User user);

    User getUserLogged();
}
