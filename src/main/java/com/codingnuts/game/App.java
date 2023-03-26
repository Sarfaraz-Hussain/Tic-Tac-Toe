package com.codingnuts.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.paint.Color.*;

public class App extends Application {

    private static Scene scene;
    private final GridPane gridPane = new GridPane();
    private final BorderPane borderPane = new BorderPane();
    private final Label title = new Label("Tic Tac Toe");
    private final Button restartButton = new Button("Restart");
    private final Button[] buttons = new Button[9];
    private final int[] gameState = {3, 3, 3, 3, 3, 3, 3, 3, 3};

    private final int[][] winingPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Font font = Font.font("Roboto", FontWeight.BOLD, 30);
    private boolean gameOver = false;
    private int activePlayer = 0;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.createGUI();
        this.handleEvent();
        Scene scene = new Scene(borderPane, 550, 650);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/images/tic-tac-toe.png"));
        stage.show();
    }

    private void createGUI() {
        title.setFont(font);
        restartButton.setFont(font);
        restartButton.setDisable(true);
        restartButton.setTextFill(GREEN);
        restartButton.setStyle("-fx-base: white;");
        title.setTextFill(GREEN);
        borderPane.setTop(title);
        borderPane.setBottom(restartButton);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(restartButton, Pos.CENTER);
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        int label = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setFont(font);
                button.setTextFill(GREEN);
                button.setBorder(new Border(new BorderStroke(GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                button.setId(label + "");
                button.setPrefWidth(150);
                button.setPrefHeight(150);
                button.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(0), Insets.EMPTY)));
                gridPane.add(button, j, i);
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(0), Insets.EMPTY)));
                buttons[label] = button;
                label++;
            }
        }
        borderPane.setCenter(gridPane);
        borderPane.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(0), Insets.EMPTY)));
    }

    private void handleEvent() {
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 9; i++) {
                    gameState[i] = 3;
                    buttons[i].setGraphic(null);
                    buttons[i].setBackground(null);
                    buttons[i].setBorder(new Border(new BorderStroke(GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                    gameOver = false;
                    restartButton.setDisable(true);
                }
            }
        });

        for (Button btn : buttons) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Button currentBtn = (Button) actionEvent.getSource();
                    String ids = currentBtn.getId();
                    int idI = Integer.parseInt(ids);
                    if (!gameOver) {
                        if (gameState[idI] == 3) {
                            if (activePlayer == 1) {
                                currentBtn.setGraphic(new ImageView(new Image("file:src/main/resources/images/play1.png")));
                                gameState[idI] = activePlayer;
                                checkForWinner();
                                checkForDraw();
                                activePlayer = 0;
                            } else {
                                currentBtn.setGraphic(new ImageView(new Image("file:src/main/resources/images/play2.png")));
                                gameState[idI] = activePlayer;
                                checkForWinner();
                                checkForDraw();
                                activePlayer = 1;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setContentText("Place is already Occupied");
                            alert.show();
                        }
                    }
                }
            });
        }
    }

    private void checkForDraw() {
        int flag = -1;
        if (!gameOver) {
            for (int i = 0; i < 9; i++) {
                if (gameState[i] == 3) {
                    flag = 0;
                    break;
                }
            }
            if (flag == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Draw Message");
                alert.setContentText("Match Draw restart the game please");
                alert.show();
            }
            restartButton.setDisable(false);
        }
    }

    private void checkForWinner() {
        if (!gameOver) {
            for (int i = 0; i < winingPositions.length; i++) {
                if (gameState[winingPositions[i][0]] == gameState[winingPositions[i][1]] && gameState[winingPositions[i][1]] == gameState[winingPositions[i][2]] && gameState[winingPositions[i][1]] != 3) {
                    buttons[winingPositions[i][0]].setBackground(new Background(new BackgroundFill(GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    buttons[winingPositions[i][1]].setBackground(new Background(new BackgroundFill(GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    buttons[winingPositions[i][2]].setBackground(new Background(new BackgroundFill(GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    gameOver = true;
                    restartButton.setDisable(false);
                    break;
                }
            }
        }
    }

}