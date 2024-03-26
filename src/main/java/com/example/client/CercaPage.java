package com.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.example.client.services.GetStoriesService;
import com.example.client.services.GetStoriesServiceAsync;
import com.example.shared.Story;
 
public class CercaPage extends Composite {
    private GetStoriesServiceAsync getStoriesAsync = GWT.create(GetStoriesService.class);
    
 
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

        //lista di ricerca 
        HorizontalPanel listPanel = new HorizontalPanel();
        ListBox storiesList = new ListBox();
        listPanel.add(storiesList);

        
        searchPanel.add(searchBox);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(listPanel);

        listPanel.setVisible(false);
 
        // Risultati della ricerca
        mainPanel.add(resultsTable);
 
        // Aggiungi stili CSS
        mainPanel.setStyleName("mainPanel");
        resultsTable.setStyleName("resultsTable");

        // Metodo bottone di ricerca 
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String searchText = searchBox.getText().trim();
                if (!searchText.isEmpty()) {
                    //Chiamata RPC per ottenere la storia in base al nome
                    getStoriesAsync.getStoryByName(searchText, new AsyncCallback<Story>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            // Gestire eventuali errori
                            Window.alert("Errore durante la ricerca della storia: " + caught.getMessage());
                        }
        
                        @Override
                        public void onSuccess(Story story) {
                            if (story != null) {
                                // Se la storia è stata trovata, aggiungila alla ListBox
                                storiesList.clear();
                                storiesList.addItem(story.getNome());
                                Window.alert("Baggio 30");
                            } else {
                                // Se la storia non è stata trovata, mostra un messaggio di avviso
                                Window.alert("Nessuna storia trovata con il nome '" + searchText + "'");
                            }
                        }
                    });
                } else {
                    Window.alert("Inserisci un nome di storia valido");
                }
            }
                
            });
            
        
    }
}

