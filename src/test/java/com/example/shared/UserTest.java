package com.example.shared;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        User user = new User("testUser", "password123");
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("newPassword");
        assertEquals("newUser", user.getUsername());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testToString() {
        User user = new User("testUser", "password123");
        String expectedToString = "Username: testUser\nPassword: password123";
        assertEquals(expectedToString, user.toString());
    }

    @Test
public void testEquals() {
    User user1 = new User("testUser", "password123");
    User user3 = new User("differentUser", "password123");
    User user4 = new User("testUser", "password123");

    assertTrue(user1.equals(user4)); // stessi username e password
    assertFalse(user1.equals(user3)); // username diversi, stessa password
    assertTrue(user1.equals(user1)); // riflessivo
    assertFalse(user1.equals(null)); // verifica null
    assertFalse(user1.equals("testUser")); // verifica tipo
}


    @Test
    public void testHashCode() {
        User user1 = new User("testUser", "password123");
        User user2 = new User("testUser", "differentPassword");
        assertEquals(user1.hashCode(), user2.hashCode()); // Same usernames
    }

    @Test
    public void testCompareTo() {
        User user1 = new User("abc", "password123");
        User user2 = new User("xyz", "password123");
        User user4 = new User("abc", "password123");

        assertTrue(user1.compareTo(user2) < 0); // "abc" < "xyz"
        assertTrue(user2.compareTo(user1) > 0); // "xyz" > "abc"
        assertTrue(user1.compareTo(user4) == 0); // same username and password
    }
}