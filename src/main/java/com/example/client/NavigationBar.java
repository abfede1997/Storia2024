package com.example.client;


import com.example.client.services.LoginService;
import com.example.client.services.LoginServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class NavigationBar extends HorizontalPanel {
    private LoginServiceAsync loginService = GWT.create(LoginService.class);

    public NavigationBar() {
        addStyleName("navbar"); 

        Button homeButton = new Button("Home");
        homeButton.addStyleName("navButton");
        homeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //app.goToHomePage();
            }
        });
        add(homeButton);

        FlexTable layoutTable = new FlexTable();
        layoutTable.setWidth("100%"); 
        Button logoutButton = new Button("LOGOUT");
        layoutTable.setWidget(0, 1, logoutButton);
        logoutButton.addStyleName("logoutButton");
        logoutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                logout(null);
            }
        });
        add(logoutButton);

    }

    public void logout(Sweng2024 app) {
        loginService.logout(new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Errore durante il logout: " + throwable.getMessage());
            }
    
            @Override
            public void onSuccess(Void result) {
                // Logout riuscito, reindirizza l'utente alla pagina di accesso o a una pagina di logout confermativa
                Window.alert("Logout eseguito con successo!");
                // Reindirizza l'utente alla pagina di accesso o a una pagina di logout confermativa
                app.showSignUpPage();
            }
        });
    }

}

