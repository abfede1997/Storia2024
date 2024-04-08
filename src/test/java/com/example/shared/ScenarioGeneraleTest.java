package com.example.shared;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class ScenarioGeneraleTest {

    @Test
    public void testScenarioImplementation() {
        Map<Integer, Scenario> proseguimento = new HashMap<>();
        ScenarioGenerale scenario = new Scenario(1, "Descrizione", "Azione", "Raccogli", "Usa", "Risposta");
        assertEquals(1, scenario.getId());
        assertEquals("Descrizione", scenario.getDescrizione());
        assertEquals("Azione", scenario.getAzione());
        assertEquals(proseguimento, scenario.getProseguimento());

        scenario.setDescrizione("Nuova Descrizione");
        assertEquals("Nuova Descrizione", scenario.getDescrizione());

        scenario.setId(2);
        assertEquals(2, scenario.getId());
    }
}
