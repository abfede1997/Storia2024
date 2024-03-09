package com.example.client;

import com.example.client.services.GetStoriesService;
import com.example.client.services.GetStoriesServiceAsync;
import com.example.shared.Story;
import com.example.shared.Scenario;
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

    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel mainStoryPanel = new VerticalPanel();
    private HorizontalPanel descriptionPanel = new HorizontalPanel();
    private HorizontalPanel buttonsPanel = new HorizontalPanel();


    public GiocaPage() {
        initWidget(this.vPanel);

        Label storyName = new Label();
        Label storyDescription = new Label();

        mainStoryPanel.add(storyName);
        mainStoryPanel.add(storyDescription);

        Label scenarioDescription = new Label();
        descriptionPanel.add(scenarioDescription);
        mainStoryPanel.add(descriptionPanel);

        List<Button> actions = new ArrayList<>();
        mainStoryPanel.add(buttonsPanel);






        // Aggiungi i componenti della pagina di ricerca
        Label titleLabel = new Label("Pagina di Gioca Storia");
        ListBox storiesList = new ListBox();
        Button startStory = new Button("Play!");

        vPanel.add(titleLabel);
        vPanel.add(storiesList);
        vPanel.add(startStory);
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

                    storyName.setText(chosenStory.getNome());
                    storyDescription.setText(chosenStory.getDescrizione());

                    currentScenario = chosenStory.getInizio();

                    constructCurrentScenario(currentScenario, scenarioDescription, actions);

                    mainStoryPanel.setVisible(true);
                }
            }
        });






    }

    private void constructCurrentScenario(Scenario currentScenario, Label scenarioDescription, List<Button> actions) {
        scenarioDescription.setText(currentScenario.getDescrizione());

        currentScenario.getProseguimento().forEach((id, scenario) -> {
            Button tmp = new Button(scenario.getAzione());

            tmp.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    if(!scenario.getProseguimento().isEmpty()) {
                        actions.clear();
                        buttonsPanel.clear();

                        constructCurrentScenario(scenario, scenarioDescription, actions);
                    }
                }
            });

            actions.add(tmp);
        });

        actions.forEach(button -> {
            buttonsPanel.add(button);
        });
    }
}
