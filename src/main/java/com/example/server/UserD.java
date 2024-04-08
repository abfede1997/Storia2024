package com.example.server;

import com.example.shared.User;

// Qui i metodi sono solo menzionati, sono implementati in UserDAOImpl
public interface UserD {

    public User getUser(String username, String password);

    public boolean addUser(User newUser);

    public User getUserByUsername(String username);

    public boolean setUserLogged(User user);

    public User getUserLogged();
}
