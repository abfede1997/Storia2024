package com.example.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.NavigableSet;

import org.mapdb.DB;

import com.example.shared.User;

public class HomePage extends Composite {

    VerticalPanel vPanel = new VerticalPanel();

    public HomePage(Sweng2024 app) {

        this.vPanel.addStyleName("wrapper");

        Button scriviButton = new Button("Scrivi Storia");
        scriviButton.addStyleName("homeButton");
        vPanel.add(scriviButton);

        Button cercaButton = new Button("Ricerca Storia");
        cercaButton.addStyleName("homeButton");
        vPanel.add(cercaButton);

        Button giocaButton = new Button("Gioca Storia");
        giocaButton.addStyleName("homeButton");
        vPanel.add(giocaButton);

        Button pagaButton = new Button("Paga Abbonamento");
        pagaButton.addStyleName("homeButton");
        vPanel.add(pagaButton);

        Button logoutButton = new Button("Logout");
        logoutButton.addStyleName("homeButton");
        vPanel.add(logoutButton);
        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                app.goToLoginPage();
            }
        });
        
        initWidget(this.vPanel);

        // Aggiunta di gestori di eventi ai pulsanti per gestire la navigazione
        cercaButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                // Naviga alla nuova pagina quando il pulsante "Ricerca Storia" viene cliccato
                History.newItem("cercaPage");
            }
        });

        scriviButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                // Naviga alla nuova pagina quando il pulsante "Scrivi Storia" viene cliccato
                History.newItem("scriviPage");
            }
        });

        giocaButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                // Naviga alla nuova pagina quando il pulsante "Gioca Storia" viene cliccato
                History.newItem("giocaPage");
            }
        });

        pagaButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                // Naviga alla nuova pagina quando il pulsante "Paga Abbonamento" viene cliccato
                History.newItem("pagaPage");
            }
        });
    }

}