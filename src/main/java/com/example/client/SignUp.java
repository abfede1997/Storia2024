package com.example.client;

import com.example.client.services.RegisterService;
import com.example.client.services.RegisterServiceAsync;
import com.example.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class SignUp extends Composite {
    
    HorizontalPanel hPanel = new HorizontalPanel();
    private RegisterServiceAsync registerService = GWT.create(RegisterService.class);

    public SignUp(Sweng2024 app) {

        this.hPanel = new HorizontalPanel();
        this.hPanel.addStyleName("signupContainer");

        
        VerticalPanel vPanel1 = new VerticalPanel();
        vPanel1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel1.addStyleName("signupSection");

        VerticalPanel vPanel2 = new VerticalPanel();
        vPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel2.addStyleName("signupSection");


        
        VerticalPanel vPanel2a = new VerticalPanel();
        vPanel2a.addStyleName("signupForm");
        vPanel2a.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        

        Label titleLabel = new Label("ENTRA IN STORIA 2024");
        titleLabel.addStyleName("signupTitle");
        vPanel2a.add(titleLabel);

        TextBox usernameBox = new TextBox();
        usernameBox.getElement().setPropertyString("placeholder", "Username");
        usernameBox.addStyleName("signupInput");
        vPanel2a.add(usernameBox);

        PasswordTextBox passwordBox = new PasswordTextBox();
        passwordBox.addStyleName("signupInput");
        passwordBox.getElement().setPropertyString("placeholder", "Password");
        vPanel2a.add(passwordBox);

        Button registerButton = new Button("Register");
        registerButton.addStyleName("registerButton");
        vPanel2a.add(registerButton);

         // Creazione del link per il login
         Anchor loginLink = new Anchor("Hai già un account? Effettua il login", false);
         loginLink.addClickHandler(new ClickHandler() {
             @Override
             public void onClick(ClickEvent event) {
                 // Metodo per andare alla pagina di login
                 app.showLoginPage();
             }
         });
         vPanel2a.add(loginLink);

        registerButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent click) {
                registerService.setUserLogged(new User(usernameBox.getText(), passwordBox.getText()), new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) { Window.alert("error saving user logged"); }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        registerService.registerUser(usernameBox.getText(), passwordBox.getText(), new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable arg0) {
                                Window.alert("error logging in");
                            }

                            @Override
                            public void onSuccess(Boolean arg0) {
                                if (arg0) app.goToHomePage();
                                else Window.alert("Account già esistente");
                            }
                        });
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
