package com.example.shared;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Story implements Serializable, Comparable<Story> {
    private String nome;
    private String descrizione;
    private Scenario inizio;
    private String categoria;
    private String autore;

    private List<String> inventario;
    public Story(final String nome, final String descrizione, final Scenario inizio, final String categoria, final String autore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.inizio = inizio;
        this.categoria = categoria;
        this.autore = autore;
        this.inventario = new ArrayList<>();
    }

    public Story() {
    }

    public String getNome() {
        return nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public Scenario getInizio() {
        return inizio;
    }
    public List<String> getInventario(){ 
        return inventario; 
    }
    public String getCategoria() { 
        return categoria; 
    }

    public String getAutore() {
        return autore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return Objects.equals(nome, story.nome) && Objects.equals(descrizione, story.descrizione) && Objects.equals(inizio, story.inizio) && Objects.equals(categoria, story.categoria) && Objects.equals(autore, story.autore) && Objects.equals(inventario, story.inventario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descrizione, inizio, categoria, autore, inventario);
    }

    @Override
    public int compareTo(@NotNull Story o) {
        int nomeComparison = this.nome.compareTo(o.getNome());
        int descComparison = this.descrizione.compareTo(o.getDescrizione());
        if (nomeComparison != 0) {
            return nomeComparison;
        } else if (descComparison != 0) {
            return descComparison;
        }
        return this.inizio.compareTo(o.inizio);
    }
}
