package com.example.shared;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class ScenarioTest {

    @Test
    public void testConstructorAndGetters() {
        Map<Integer, Scenario> proseguimento = new HashMap<>();
        Scenario scenario = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        assertEquals(1, scenario.getId());
        assertEquals("Descrizione", scenario.getDescrizione());
        assertEquals("Azione", scenario.getAzione());
        assertEquals("Raccogli", scenario.getRaccogliOggetto());
        assertEquals("Usa", scenario.getUsaOggetto());
        assertEquals("Risposta", scenario.getIndovinello());
        assertEquals(proseguimento, scenario.getProseguimento());
    }

    @Test
    public void testEquals() {
        @SuppressWarnings("unused")
        Map<Integer, Scenario> proseguimento = new HashMap<>();
        Scenario scenario1 = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        Scenario scenario2 = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        Scenario scenario3 = new Scenario(2, "Different Description", "Azione", "Raccogli", "Usa", "Risposta");

        assertTrue(scenario1.equals(scenario2)); // same id and attributes
        assertFalse(scenario1.equals(scenario3)); // different id
        assertFalse(scenario1.equals(null)); // null check
        assertFalse(scenario1.equals("Descrizione")); // type check
    }

    @Test
    public void testHashCode() {
        @SuppressWarnings("unused")
        Map<Integer, Scenario> proseguimento = new HashMap<>();
        Scenario scenario1 = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        Scenario scenario2 = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        assertEquals(scenario1.hashCode(), scenario2.hashCode()); // Same attributes
    }

    @Test
    public void testCompareTo() {
        @SuppressWarnings("unused")
        Map<Integer, Scenario> proseguimento = new HashMap<>();
        Scenario scenario1 = new Scenario(1, "ABC", "Azione", "Raccogli", "Usa", "Risposta");
        Scenario scenario2 = new Scenario(2, "XYZ", "Azione", "Raccogli", "Usa", "Risposta");
        Scenario scenario3 = new Scenario(1, "ABC", "Different Action", "Raccogli", "Usa", "Risposta");

        assertTrue(scenario1.compareTo(scenario2) < 0); // "ABC" < "XYZ"
        assertTrue(scenario2.compareTo(scenario1) > 0); // "XYZ" > "ABC"
        assertTrue(scenario1.compareTo(scenario3) < 0); // same id, "Azione" < "Different Action"
    }
}
