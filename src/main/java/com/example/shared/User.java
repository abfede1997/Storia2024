package com.example.shared;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Comparable<User> {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + "\n" +
                "Password: " + this.password;
    }

    @Override
    public int compareTo(User otherUser) {
        int usernameComparison = this.username.compareTo(otherUser.getUsername());
        if (usernameComparison != 0) {
            return usernameComparison;
        }
        return this.password.compareTo(otherUser.getPassword());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User otherUser = (User) obj;
        return Objects.equals(this.username, otherUser.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
