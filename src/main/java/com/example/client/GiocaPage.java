package com.example.client;

import com.example.client.services.GetStoriesService;
import com.example.client.services.GetStoriesServiceAsync;
import com.example.client.services.LoginService;
import com.example.client.services.LoginServiceAsync;
import com.example.shared.Scenario;
import com.example.shared.Story;
import com.example.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.*;

public class GiocaPage extends Composite {
    private GetStoriesServiceAsync getStoriesAsync = GWT.create(GetStoriesService.class);
    private LoginServiceAsync loginService = GWT.create(LoginService.class);

    private List<Story> playStories;
    private Story chosenStory;
    private Scenario currentScenario;
    private List<String> oggettiRaccolti;
    private String loggedUser;
    private Map<Integer, TextBox> descChanges = new HashMap<>();
    private Map<Integer, TextBox> actionChanges = new HashMap<>();


    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel mainStoryPanel = new VerticalPanel();
    private HorizontalPanel descriptionPanel = new HorizontalPanel();
    private HorizontalPanel buttonsPanel = new HorizontalPanel();
    private VerticalPanel horPanelColumns = new VerticalPanel();


    public GiocaPage() {
        initWidget(this.vPanel);

        this.oggettiRaccolti = new ArrayList<>();

        Label storyName = new Label();
        Label storyDescription = new Label();
        Label storyCategory = new Label();
        Label storyAuthor = new Label();

        mainStoryPanel.add(storyName);
        mainStoryPanel.add(storyDescription);
        mainStoryPanel.add(storyCategory);
        mainStoryPanel.add(storyAuthor);

        Label scenarioDescription = new Label();
        descriptionPanel.add(scenarioDescription);
        mainStoryPanel.add(descriptionPanel);

        List<Button> actions = new ArrayList<>();
        mainStoryPanel.add(buttonsPanel);


        // Aggiungi i componenti della pagina di ricerca
        Label titleLabel = new Label("Pagina di Gioca Storia");
        ListBox storiesList = new ListBox();

        Button startStory = new Button("Play!");

        Button removeStory = new Button("Rimuovi Storia");
        removeStory.setVisible(false);

        Button modifyStory = new Button("Modifica Storia");
        modifyStory.setVisible(false);

        Button saveScenario = new Button("Salva Storia Corrente");
        saveScenario.setVisible(false);

        Button loadScenario = new Button("Carica Storia Salvata");
        loadScenario.setVisible(false);

        vPanel.add(titleLabel);
        vPanel.add(storiesList);
        vPanel.add(startStory);
        vPanel.add(removeStory);
        vPanel.add(modifyStory);

        mainStoryPanel.setVisible(false);





        storiesList.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                if(story.isPresent() && story.get().getAutore().equals(loggedUser)) {
                    removeStory.setVisible(true);
                    modifyStory.setVisible(true);
                } else {
                    removeStory.setVisible(false);
                    modifyStory.setVisible(false);
                }
            }
        });

        saveScenario.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                getStoriesAsync.saveCurrentStory(loggedUser, chosenStory, currentScenario, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("Errore! Storia non salvata");
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Window.alert("Storia salvata con successo!");
                    }
                });
            }
        });

        loadScenario.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                getStoriesAsync.loadCurrentStory(loggedUser, chosenStory, new AsyncCallback<Pair<Scenario, Story>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("error");
                    }

                    @Override
                    public void onSuccess(Pair<Scenario, Story> scenarioStoryPair) {
                        if(scenarioStoryPair.getA() == null || scenarioStoryPair.getB() == null){
                            Window.alert("Nessun scenario salvato!");
                        } else {
                            horPanelColumns.clear();
                            actions.clear();
                            buttonsPanel.clear();
                            oggettiRaccolti.clear();
                            chosenStory.getInventario().clear();

                            chosenStory = scenarioStoryPair.getB();
                            currentScenario = scenarioStoryPair.getA();
                            oggettiRaccolti.addAll(chosenStory.getInventario());

                            constructCurrentScenario(currentScenario, scenarioDescription, actions);
                        }
                    }
                });
            }
        });

        loginService.getUserLogged(new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("error");
            }

            @Override
            public void onSuccess(User user) {
                loggedUser = user.getUsername();

                getStoriesAsync.getStories(new AsyncCallback<List<Story>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("error 2");
                    }

                    @Override
                    public void onSuccess(List<Story> stories) {
                        playStories = stories;
                        playStories.forEach(s -> {
                            storiesList.addItem(s.getNome());
                        });

                        Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                        if(story.isPresent() && story.get().getAutore().equals(loggedUser)) {
                            removeStory.setVisible(true);
                            modifyStory.setVisible(true);
                        } else {
                            modifyStory.setVisible(false);
                            removeStory.setVisible(false);
                        }
                    }
                });
            }
        });


        startStory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(!playStories.isEmpty() && storiesList.getItemCount() > 0) {
                    Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                    story.ifPresent(value -> chosenStory = value);

                    horPanelColumns.clear();
                    actions.clear();
                    buttonsPanel.clear();
                    oggettiRaccolti.clear();
                    chosenStory.getInventario().clear();

                    storyName.setText(chosenStory.getNome());
                    storyDescription.setText(chosenStory.getDescrizione());
                    storyCategory.setText(chosenStory.getCategoria());
                    storyAuthor.setText(chosenStory.getAutore());

                    currentScenario = chosenStory.getInizio();

                    constructCurrentScenario(currentScenario, scenarioDescription, actions);

                    mainStoryPanel.setVisible(true);
                    saveScenario.setVisible(true);
                    loadScenario.setVisible(true);
                }
            }
        });

        removeStory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(!playStories.isEmpty() && storiesList.getItemCount() > 0) {
                    Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                    story.ifPresent(value -> chosenStory = value);
                    horPanelColumns.clear();
                    mainStoryPanel.setVisible(false);
                    saveScenario.setVisible(false);
                    loadScenario.setVisible(false);

                    getStoriesAsync.removeStoryByName(chosenStory, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("error");
                        }
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            if(aBoolean){
                                Window.alert("Storia rimossa con successo!");


                                storiesList.clear();
                                getStoriesAsync.getStories(new AsyncCallback<List<Story>>() {
                                    @Override
                                    public void onFailure(Throwable throwable) {
                                        Window.alert("error");
                                    }

                                    @Override
                                    public void onSuccess(List<Story> stories) {
                                        playStories = stories;
                                        playStories.forEach(s -> {
                                            storiesList.addItem(s.getNome());
                                        });

                                    }
                                });
                            } else {
                                Window.alert("La storia non è stata rimossa");
                            }
                        }
                    });
                }
            }
        });

        modifyStory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(!playStories.isEmpty() && storiesList.getItemCount() > 0) {
                    Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                    story.ifPresent(value -> chosenStory = value);

                    horPanelColumns.clear();
                    mainStoryPanel.setVisible(false);
                    saveScenario.setVisible(false);
                    loadScenario.setVisible(false);
                    descChanges.clear();
                    actionChanges.clear();

                    HorizontalPanel labelsPanels = new HorizontalPanel();

                    Label idColumn = new Label("ID");
                    Label descColumn = new Label("Descrizione");
                    Label azioneColumn = new Label("Azione");

                    labelsPanels.add(idColumn);
                    labelsPanels.add(descColumn);
                    labelsPanels.add(azioneColumn);

                    horPanelColumns.add(labelsPanels);

                    modifyScenario(chosenStory.getInizio(), horPanelColumns);

                    Button saveChanges = new Button("Save Changes");
                    horPanelColumns.add(saveChanges);

                    saveChanges.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            assignChanges(chosenStory.getInizio());

                            getStoriesAsync.modifyStoryByName(chosenStory, new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable throwable) {
                                    Window.alert("La storia non è stata aggiornata");
                                }

                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    Window.alert("Storia aggiornata con successo!");
                                    horPanelColumns.clear();
                                    mainStoryPanel.setVisible(false);
                                    saveScenario.setVisible(false);
                                    loadScenario.setVisible(false);
                                    descChanges.clear();
                                    actionChanges.clear();
                                }
                            });
                        }
                    });

                    vPanel.add(horPanelColumns);



                }
            }
        });



        vPanel.add(mainStoryPanel);
        vPanel.add(saveScenario);
        vPanel.add(loadScenario);

    }

    private void modifyScenario(Scenario scenario, VerticalPanel vPanel) {

        HorizontalPanel horPanel = new HorizontalPanel();

        Label id = new Label(String.valueOf(scenario.getId()));

        TextBox desc = new TextBox();
        desc.setText(scenario.getDescrizione());
        descChanges.put(scenario.getId(), desc);

        TextBox azione = new TextBox();
        azione.setText(scenario.getAzione());
        actionChanges.put(scenario.getId(),azione);

        horPanel.add(id);
        horPanel.add(desc);
        horPanel.add(azione);

        vPanel.add(horPanel);

        if(!scenario.getProseguimento().isEmpty()) {
            scenario.getProseguimento().forEach((t, s) -> modifyScenario(s, vPanel));
        }

    }

    private void assignChanges(Scenario scenario) {
        scenario.setDescrizione(descChanges.get(scenario.getId()).getText());
        scenario.setAzione(actionChanges.get(scenario.getId()).getText());

        if(!scenario.getProseguimento().isEmpty()){
            scenario.getProseguimento().forEach((id, s) -> assignChanges(s));
        }
    }


    private void constructCurrentScenario(Scenario currentScenario, Label scenarioDescription, List<Button> actions) {
        scenarioDescription.setText(currentScenario.getDescrizione());
        this.currentScenario = currentScenario;

        if(currentScenario.getIndovinello().isEmpty()) {

            if (!currentScenario.getRaccogliOggetto().isEmpty() && !oggettiRaccolti.contains(currentScenario.getRaccogliOggetto())) {
                Button tmp = new Button("Raccogli " + currentScenario.getRaccogliOggetto());
                tmp.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        chosenStory.getInventario().add(currentScenario.getRaccogliOggetto());
                        oggettiRaccolti.add(currentScenario.getRaccogliOggetto());

                        actions.clear();
                        buttonsPanel.clear();
                        constructCurrentScenario(currentScenario, scenarioDescription, actions);
                    }
                });
                actions.add(tmp);
            }

            currentScenario.getProseguimento().forEach((id, scenario) -> {
                Button tmp;

                if (!scenario.getUsaOggetto().isEmpty() && chosenStory.getInventario().contains(scenario.getUsaOggetto())) {
                    tmp = new Button("Usa " + scenario.getUsaOggetto());
                    tmp.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            actions.clear();
                            buttonsPanel.clear();
                            chosenStory.getInventario().remove(scenario.getUsaOggetto());
                            constructCurrentScenario(scenario, scenarioDescription, actions);
                        }
                    });
                    actions.add(tmp);
                } else if (scenario.getUsaOggetto().isEmpty()) {
                    tmp = new Button(scenario.getAzione());
                    tmp.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            actions.clear();
                            buttonsPanel.clear();
                            constructCurrentScenario(scenario, scenarioDescription, actions);
                        }
                    });
                    actions.add(tmp);
                }
            });

            actions.forEach(button -> {
                buttonsPanel.add(button);
            });
        } else {
            TextBox answer = new TextBox();
            Button submitAnswer = new Button("Rispondi");

            buttonsPanel.add(answer);
            buttonsPanel.add(submitAnswer);

            submitAnswer.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    if(!answer.getText().isEmpty() && answer.getText().equals(currentScenario.getIndovinello())){
                        actions.clear();
                        buttonsPanel.clear();
                        constructCurrentScenario(currentScenario.getProseguimento().entrySet().stream().findFirst().get().getValue(), scenarioDescription, actions);
                    }
                }
            });
        }
    }
}
