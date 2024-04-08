package com.example.shared;

import org.junit.Test;
import static org.junit.Assert.*;

public class StoryTest {

    @Test
    public void testConstructorAndGetters() {
        Scenario scenario = new Scenario(0, "Start", "Description", null, null, null);
        Story story = new Story("Test Story", "Description", scenario, "Adventure", "Author");
        assertEquals("Test Story", story.getNome());
        assertEquals("Description", story.getDescrizione());
        assertEquals(scenario, story.getInizio());
        assertEquals("Adventure", story.getCategoria());
        assertEquals("Author", story.getAutore());
    }

    @Test
    public void testInventario() {
        Story story = new Story();
    }
        

    @Test
    public void testEquals() {
        Scenario scenario1 = new Scenario(0, "Start", "Description", null, null, null);
        Story story1 = new Story("Test Story", "Description", scenario1, "Adventure", "Author");
        Story story2 = new Story("Test Story", "Different Description", scenario1, "Adventure", "Author");
        Story story3 = new Story("Different Story", "Description", scenario1, "Adventure", "Author");
        Story story4 = new Story("Test Story", "Description", scenario1, "Adventure", "Author");
        
        assertFalse(story1.equals(null)); // null check
        assertFalse(story1.equals("Test Story")); // type check
        assertFalse(story1.equals(story2)); // different description
        assertFalse(story1.equals(story3)); // different name
        assertTrue(story1.equals(story4)); // same values
    }

    @Test
    public void testHashCode() {
        Scenario scenario = new Scenario(0, "Start", "Description", null, null, null);
        Story story1 = new Story("Test Story", "Description", scenario, "Adventure", "Author");
        Story story2 = new Story("Test Story", "Different Description", scenario, "Adventure", "Author");
        assertNotEquals(story1.hashCode(), story2.hashCode()); // Different descriptions
    }

    @Test
    public void testCompareTo() {
        Scenario scenario1 = new Scenario(0, "Start", "Description", null, null, null);
        Scenario scenario2 = new Scenario(0, "Begin", "Another Description", null, null, null);
        Story story1 = new Story("ABC", "Description", scenario1, "Adventure", "Author");
        Story story2 = new Story("XYZ", "Description", scenario2, "Adventure", "Author");
        Story story3 = new Story("ABC", "Description", scenario1, "Adventure", "Author");

        assertTrue(story1.compareTo(story2) < 0); // "ABC" < "XYZ"
        assertTrue(story2.compareTo(story1) > 0); // "XYZ" > "ABC"
        assertTrue(story1.compareTo(story3) == 0); // same nome and descrizione
    }
}
