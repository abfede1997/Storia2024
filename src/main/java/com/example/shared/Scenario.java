package com.example.shared;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scenario implements ScenarioGenerale, Serializable, Comparable<Scenario> {
    private int id;
    private String descrizione;
    private String azione;
    private String raccogliOggetto;
    private String usaOggetto;
    private String indovinelloRisposta;
    private Map<Integer, Scenario> proseguimento;

    public Scenario(final int id,
                    final String descrizione,
                    final String azione,
                    final String raccogliOggetto,
                    final String usaOggetto,
                    final String indovinelloRisposta) {
        this.id = id;
        this.descrizione = descrizione;
        this.azione = azione;
        this.raccogliOggetto = raccogliOggetto;
        this.usaOggetto = usaOggetto;
        this.indovinelloRisposta = indovinelloRisposta;
        this.proseguimento = new HashMap<>();
    }

    public Scenario() {
    }

    @Override
    public String getDescrizione() {
        return this.descrizione;
    }

    public String getIndovinello() {
        return indovinelloRisposta;
    }
    public String getRaccogliOggetto() {
        return raccogliOggetto;
    }
    public String getUsaOggetto() { return usaOggetto; }
    @Override
    public void setDescrizione(final String descrizione) {
        this.descrizione = descrizione;
    }
    @Override
    public int getId() {
        return id;
    }
    public void setAzione(String azione) {
        this.azione = azione;
    }
    @Override
    public void setId(final int id) {
        this.id = id;
    }
    @Override
    public String getAzione() {
        return this.azione;
    }
    @Override
    public Map<Integer, Scenario> getProseguimento() {
        return this.proseguimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario scenario = (Scenario) o;
        return id == scenario.id && Objects.equals(descrizione, scenario.descrizione) && Objects.equals(azione, scenario.azione) && Objects.equals(raccogliOggetto, scenario.raccogliOggetto) && Objects.equals(usaOggetto, scenario.usaOggetto) && Objects.equals(indovinelloRisposta, scenario.indovinelloRisposta) && Objects.equals(proseguimento, scenario.proseguimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrizione, azione, raccogliOggetto, usaOggetto, indovinelloRisposta, proseguimento);
    }

    @Override
    public int compareTo(@NotNull Scenario o) {
        int descComparison = this.descrizione.compareTo(o.descrizione);
        int azioneComparison = this.azione.compareTo(o.azione);

        if(this.id != o.id) {
            return this.id - o.id;
        } else if (descComparison != 0) {
            return descComparison;
        }
        return azioneComparison;
    }

    public static void printStory(Scenario scenario) {
        console(scenario.descrizione);
        console(scenario.azione);
        scenario.getProseguimento().forEach((key1, value1) -> console(scenario.getId() + " " + key1));
        scenario.getProseguimento().forEach((key, value) -> printStory(value));
    }

    public static native void console(String text);
}
