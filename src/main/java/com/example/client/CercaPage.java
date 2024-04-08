package com.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;

import com.example.client.services.GetStoriesService;
import com.example.client.services.GetStoriesServiceAsync;
import com.example.shared.Story;
 
public class CercaPage extends Composite {
    private List<Story> watchStories;
    private GetStoriesServiceAsync getStoriesAsync = GWT.create(GetStoriesService.class);
    
 
    private VerticalPanel mainPanel = new VerticalPanel();
    private Grid searchPanel = new Grid(3,3);
    private FlexTable resultsTable = new FlexTable();
    
 
    public CercaPage() {

        initWidget(mainPanel);
 
        // Titolo della pagina
        Label titleLabel = new Label("Catalogo di Storie");
        titleLabel.addStyleName("titoli");
        mainPanel.add(titleLabel);
 
        // Pannello di ricerca
        searchPanel.addStyleName("PannelloDiRicerca");

        // Area di inserimento ricerca 
        Label titleSearch = new Label("Inserisci la ricerca:");
        titleSearch.addStyleName("inserimentoTitle");
        final TextBox searchBox = new TextBox();
        searchBox.addStyleName("inserimentoBox");

        //Menu a tendina per i filtri
        Label titleFilter = new Label("Seleziona filtro");
        titleFilter.addStyleName("filterTitle");
        ListBox filtro = new ListBox();
        filtro.addItem("Nessun Filtro");
        filtro.addItem("Nome Storia");
        filtro.addItem("Categoria");
        filtro.addItem("Autore");
        filtro.addStyleName("filter");
        //filtro.addItem("Lunghezza Storia");

        //bottone di ricerca
        Button searchButton = new Button("Cerca");
        searchButton.addStyleName("searchButton");

        //lista di ricerca risultato
        HorizontalPanel listPanel = new HorizontalPanel();
        ListBox storiesList = new ListBox();
        listPanel.add(storiesList);
        storiesList.addStyleName("listaDiRicerca");

        // Aggiungi i componenti alla griglia
        searchPanel.setWidget(0, 0, titleSearch);
        searchPanel.setWidget(1, 0, searchBox);
        searchPanel.setWidget(0, 1, titleFilter);
        searchPanel.setWidget(1, 1, filtro);
        searchPanel.setWidget(1, 2, searchButton);
        
        mainPanel.add(searchPanel);
        mainPanel.add(listPanel);

        listPanel.setVisible(false);
 
        // Risultati della ricerca
        mainPanel.add(resultsTable);
 
        // Aggiungi stili CSS
        mainPanel.setStyleName("mainPanel");
        resultsTable.setStyleName("resultsTable");

        VerticalPanel pRisultato = new VerticalPanel();
        pRisultato.addStyleName("risultato");

        // Metodo bottone di ricerca 
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String searchText = searchBox.getText().trim();
                if (filtro.getSelectedIndex() == 0) {
                    getStoriesAsync.getStories(new AsyncCallback<List<Story>>() {

                        @Override
                        public void onFailure(Throwable arg0) {
                            // TODO Auto-generated method stub
                            throw new UnsupportedOperationException("Errore nel caricamento delle storie!");
                        }
            
                        @Override
                        public void onSuccess(List<Story> stories) {
                            // TODO Auto-generated method stub
                            watchStories = stories;
                            pRisultato.clear();
                            watchStories.forEach(s -> {
                                pRisultato.add(new Label(s.getNome()));
                                mainPanel.add(pRisultato);
                            });
                        }    
                    });

                } else if (filtro.getSelectedIndex() == 1) {
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
                                    //Pannello risultato
                                    Label nomeStoria = new Label(story.getNome());
                                    pRisultato.clear();
                                    pRisultato.add(nomeStoria);
                                    //pRisultato.add(inizioStoria);
                                    mainPanel.add(pRisultato);
                                } else {
                                    // Se la storia non è stata trovata, mostra un messaggio di avviso                        
                                    Window.alert("Nessuna storia trovata con il nome '" + searchText + "'");
                                }                
                            }
                        });
                    } else {
                        Window.alert("Inserisci un nome di storia valido");
                    }

                } else if (filtro.getSelectedIndex() == 2) {
                    if (!searchText.isEmpty()) {
                        //Chiamata RPC per ottenere la storia in base al nome
                        getStoriesAsync.getAllStoriesByCategoria(searchText, new AsyncCallback<List<Story>>() {

                            @Override
                            public void onFailure(Throwable arg0) {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Errore nel caricamento delle storie per categoria!");    
                            }

                            @Override
                            public void onSuccess(List<Story> storiesC) {
                                // TODO Auto-generated method stub
                                watchStories.clear();
                                watchStories = storiesC;
                                pRisultato.clear();
                                watchStories.forEach(s -> {
                                    pRisultato.add(new Label(s.getNome()));
                                    mainPanel.add(pRisultato);
                            });
                                
                            }
                        });
                    } 

                } else if (filtro.getSelectedIndex() == 3) {
                    if (!searchText.isEmpty()) {
                        //Chiamata RPC per ottenere la storia in base al nome
                        getStoriesAsync.getAllStoriesByAutore(searchText, new AsyncCallback<List<Story>>() {

                            @Override
                            public void onFailure(Throwable arg0) {
                                // TODO Auto-generated method stub
                                throw new UnsupportedOperationException("Errore nel caricamento delle storie per autore!");    
                            }

                            @Override
                            public void onSuccess(List<Story> storiesA) {
                                // TODO Auto-generated method stub
                                watchStories.clear();
                                watchStories = storiesA;
                                pRisultato.clear();
                                watchStories.forEach(s -> {
                                    pRisultato.add(new Label(s.getNome()));
                                    mainPanel.add(pRisultato);
                            });
                                
                            }
                        });
                    } 
                    

                //} else if (filtro.getSelectedIndex() == 4) {}

                }
            } 
        });

            
        
    }
}