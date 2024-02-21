package com.example.client;

import com.example.client.services.LoginService;
import com.example.client.services.LoginServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class Login extends Composite {

    private HorizontalPanel hPanel;
    private LoginServiceAsync loginService = GWT.create(LoginService.class);

    public Login(Sweng2024 app){
        this.hPanel = new HorizontalPanel();
        this.hPanel.addStyleName("loginContainer");

        VerticalPanel vPanel1 = new VerticalPanel();
        vPanel1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel1.addStyleName("loginSection");

        VerticalPanel vPanel2 = new VerticalPanel();
        vPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel2.addStyleName("loginSection");

        VerticalPanel vPanel2a = new VerticalPanel();
        vPanel2a.addStyleName("loginForm");
        vPanel2a.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        Label titleLabel = new Label("LOGGATI IN STORIA 2024");
        titleLabel.addStyleName("loginTitle");
        vPanel2a.add(titleLabel);

        TextBox emailBox = new TextBox();
        emailBox.getElement().setPropertyString("placeholder", "Email");
        emailBox.addStyleName("loginInput");
        vPanel2a.add(emailBox);

        PasswordTextBox passwordBox = new PasswordTextBox();
        passwordBox.addStyleName("loginInput");
        passwordBox.getElement().setPropertyString("placeholder", "Password");
        vPanel2a.add(passwordBox);

        Button loginButton = new Button("Login");
        loginButton.addStyleName("loginButton");
        vPanel2a.add(loginButton);

         // Creazione del link per il signup
         Anchor signupLink = new Anchor("Non hai ancora un account? Effettua la registrazione ", false);
         signupLink.addClickHandler(new ClickHandler() {
             @Override
             public void onClick(ClickEvent event) {
                 // Metodo per andare alla pagina di login
                 app.showSignUpPage();
             }
         });
         vPanel2a.add(signupLink);

        loginButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                loginService.login(emailBox.getText(), passwordBox.getText(), new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable arg0) {
                        Window.alert("error");
                    }

                    @Override
                    public void onSuccess(Boolean arg0) {
                        if (arg0) app.goToHomePage();
                        else Window.alert("Credenziali non valide");
                    }
                });
            }
        });

        vPanel2.add(vPanel2a);


        this.hPanel.add(vPanel1);
        this.hPanel.add(vPanel2);

        initWidget(this.hPanel);

    }
    
}
