package com.example.client;

import com.example.client.services.GetStoriesService;
import com.example.client.services.GetStoriesServiceAsync;
import com.example.shared.Scenario;
import com.example.shared.Story;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GiocaPage extends Composite {
    private GetStoriesServiceAsync getStoriesAsync = GWT.create(GetStoriesService.class);

    private List<Story> playStories;
    private Story chosenStory;
    private Scenario currentScenario;
    private List<String> oggettiRaccolti;

    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel mainStoryPanel = new VerticalPanel();
    private HorizontalPanel descriptionPanel = new HorizontalPanel();
    private HorizontalPanel buttonsPanel = new HorizontalPanel();


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
        //Button removeStory = new Button("Remove Story");

        vPanel.add(titleLabel);
        vPanel.add(storiesList);
        vPanel.add(startStory);
        //vPanel.add(removeStory);
        vPanel.add(mainStoryPanel);

        mainStoryPanel.setVisible(false);

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

        startStory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(!playStories.isEmpty() && storiesList.getItemCount() > 0) {
                    Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                    story.ifPresent(value -> chosenStory = value);

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
                }
            }
        });

/*         removeStory.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if(!playStories.isEmpty() && storiesList.getItemCount() > 0) {
                    Optional<Story> story = playStories.stream().filter(s -> s.getNome().equals(storiesList.getSelectedItemText())).findAny();
                    story.ifPresent(value -> chosenStory = value);

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
        }); */




    }

    private void constructCurrentScenario(Scenario currentScenario, Label scenarioDescription, List<Button> actions) {
        scenarioDescription.setText(currentScenario.getDescrizione());

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
