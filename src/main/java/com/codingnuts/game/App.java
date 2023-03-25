package com.codingnuts.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    // GUI variables
    private static Scene scene;
    private final GridPane gridPane = new GridPane();
    private final BorderPane borderPane = new BorderPane();
    private final Label title = new Label("Tic Tac Toe");
    private final Button restartButton = new Button("Restart Button");
    private final Button[] buttons = new Button[9];
    Font font = Font.font("Roboto", FontWeight.BOLD, 30);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.createGUI();
        this.handleEvent();
        Scene scene = new Scene(borderPane, 550, 650);
        stage.setScene(scene);
        stage.show();
    }

    // This function is for creating GUI
    private void createGUI() {
        title.setFont(font);
        restartButton.setFont(font);
        borderPane.setTop(title);
        borderPane.setBottom(restartButton);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(restartButton, Pos.CENTER);
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        int label = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button(label + "");
                button.setFont(font);
                button.setId(label + "");
                button.setPrefWidth(150);
                button.setPrefHeight(150);
                gridPane.add(button, j, i);
                gridPane.setAlignment(Pos.CENTER);
                buttons[label] = button;
                label++;
            }
        }
        borderPane.setCenter(gridPane);
    }

    private void handleEvent() {
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        for(Button btn : buttons) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Button currentBtn = (Button)actionEvent.getSource();
                    String ids = currentBtn.getId();
                    int idI = Integer.parseInt(ids);
                }
            });
        }
    }

}