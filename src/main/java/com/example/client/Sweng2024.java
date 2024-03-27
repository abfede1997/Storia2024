package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class Sweng2024 implements EntryPoint {
  
  public void onModuleLoad() {
     // Configura il gestore di cambiamento della cronologia
     History.addValueChangeHandler(event -> handleHistoryChange(event.getValue()));

     // Inizialmente mostra la pagina di login
     showSignUpPage();
    }
    
  public void showSignUpPage() {
    RootPanel.get().clear();
    SignUp signUpPage = new SignUp(this);
    RootPanel.get().add(signUpPage);
  }
  public void goToHomePage() {
    // Quando si desidera andare alla pagina principale, aggiorna la cronologia
    History.newItem("home");
  }
  public void showLoginPage() {
    RootPanel.get().clear();
    Login loginPage = new Login(this);
    RootPanel.get().add(loginPage);
  }
  
  private void handleHistoryChange(String historyToken) {
    if ("home".equals(historyToken)) {
      showHomePage();
    } else if ("loginPage".equals(historyToken)) {
      showLoginPage();
    }  else if ("scriviPage".equals(historyToken)) {
      showScriviPage();
    } else if ("pagaPage".equals(historyToken)) {
      showPagaPage();
    } else if ("cercaPage".equals(historyToken)) {
      showCercaPage();
    } else if ("giocaPage".equals(historyToken)) {
      showGiocaPage();
    } else {
      showSignUpPage();
    }
  }
  private void showHomePage() {
    RootPanel.get().clear();
    HomePage home = new HomePage();
    RootPanel.get().add(home);
  } 
  private void showPagaPage() {
    RootPanel.get().clear();
    PagaPage pagaPage = new PagaPage();
    RootPanel.get().add(pagaPage);
  }
  private void showScriviPage() {
    RootPanel.get().clear();
    ScriviPage scriviPage = new ScriviPage(); // Assumi che tu abbia una classe ScriviPage
    RootPanel.get().add(scriviPage);
  }
  private void showCercaPage() {
    RootPanel.get().clear();
    CercaPage cercaPage = new CercaPage(); // Assumi che tu abbia una classe CercaPage
    RootPanel.get().add(cercaPage);
  }
  private void showGiocaPage() {
    RootPanel.get().clear();
    GiocaPage giocaPage = new GiocaPage();
    RootPanel.get().add(giocaPage);
  }
}