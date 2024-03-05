package com.example.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
 
public class CercaPage extends Composite {
 
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable resultsTable = new FlexTable();
 
    public CercaPage() {
        initWidget(mainPanel);
 
        // Titolo della pagina
        Label titleLabel = new Label("Catalogo di Storie");
        titleLabel.addStyleName("ricercaTitle");
        mainPanel.add(titleLabel);
 
        // Pannello di ricerca
        HorizontalPanel searchPanel = new HorizontalPanel();
        final TextBox searchBox = new TextBox();
        Button searchButton = new Button("Cerca");
        searchButton.addStyleName("searchButton");

        // Aggiungi il click handler al bottone di ricerca
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //populateResultsTable();
            }
        });

        searchPanel.add(searchBox);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
 
        // Risultati della ricerca
        mainPanel.add(resultsTable);
 
        // Aggiungi stili CSS
        mainPanel.setStyleName("mainPanel");
        resultsTable.setStyleName("resultsTable");


        // Simulazione di risultati di ricerca
        //simulateSearchResults();
    }
 
    // Metodo per popolare la tabella dei risultati con tutte le storie dal server
    /*private void populateResultsTable() {
        // Rimuovi eventuali risultati precedenti
        resultsTable.removeAllRows();

        // Ottieni tutte le storie dal server
        NavigableSet<Story> storySet = Database.getInstance().getStorySet();

        // Popola la tabella dei risultati con le storie ottenute
        int row = 0;
        for (Story story : storySet) {
            resultsTable.setText(row, 0, story.getNome());
            resultsTable.setText(row, 1, story.getDescrizione());
            // Aggiungi altre colonne se necessario
            row++;
        }
    }*/

   
}

