package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;


public class Sweng2024 implements EntryPoint {
  
  public void onModuleLoad() {
     // Configura il gestore di cambiamento della cronologia
     //History.addValueChangeHandler(event -> handleHistoryChange(event.getValue()));

     // Inizialmente mostra la pagina di login
     showSignUpPage();
    }
    
  private void showSignUpPage() {
    RootPanel.get().clear();
    SignUpPage signUpPage = new SignUpPage(this);
    RootPanel.get().add(signUpPage);
  }
  /*private void handleHistoryChange(String historyToken) {
    if ("home".equals(historyToken)) {
      showHomePage();
    } else if ("cercaPage".equals(historyToken)) {
      showCercaPage();
    } else if ("scriviPage".equals(historyToken)) {
      showScriviPage();
    } else if ("giocaPage".equals(historyToken)) {
      showGiocaPage();
    } else if ("pagaPage".equals(historyToken)) {
      showPagaPage();
    } else {
      showLoginPage();
    }
  } */
}