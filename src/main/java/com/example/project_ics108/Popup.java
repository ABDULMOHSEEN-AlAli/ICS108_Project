package com.example.project_ics108;
// import the FX libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Popup {
    public static boolean isRestart = false;
    public static boolean display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Win");


        GridPane popupPagePane = new GridPane();
        Scene popupPageScene = new Scene(popupPagePane);
        popupPagePane.setStyle("-fx-background-color:#61B15A");

        Label congratulations = new Label("Congratulations");

        congratulations.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;" );
        GridPane.setMargin(congratulations, new Insets(20, 20, 20, 20));

        popupPagePane.add(congratulations,0,0);
        popupPagePane.setHalignment(congratulations, HPos.CENTER);

        Button playAgain = new Button("Play Again");
        Button popExit = new Button("Exit");
        popupPagePane.add(playAgain,0,1);
        popupPagePane.add(popExit,0,2);

        playAgain.setStyle("-fx-background-color:#FFCE89;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        popExit.setStyle("-fx-background-color:#FFF76A;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        playAgain.setMinHeight(45);
        playAgain.setMinWidth(80);
        popExit.setMinHeight(45);
        popExit.setMinWidth(80);

        popupPagePane.setHalignment(playAgain, HPos.CENTER);
        popupPagePane.setHalignment(popExit, HPos.CENTER);

        GridPane.setMargin(playAgain, new Insets(0, 20, 0, 20));
        GridPane.setMargin(popExit, new Insets(10, 20, 20, 20));
        popExit.setOnAction(e -> window.close());
        playAgain.setOnAction(e -> {
            isRestart = true;
            window.close();
        } );

        window.setScene(popupPageScene);
        window.showAndWait();
        popupPageScene.setFill(Color.GREEN);

        return isRestart;
    }

    public static void changeIsRestart() {
        isRestart = false;
    }
}

