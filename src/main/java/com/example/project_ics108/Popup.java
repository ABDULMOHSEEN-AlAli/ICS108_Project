package com.example.project_ics108;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Popup {
    // Defining static variables isRestart and isExit
    public static boolean isRestart = false; // The benefit of this variable is that the function display return it so the main class know if the play again button is pressed or not.
    public static boolean isExit = false; // The benefit of this variable is that the main class chick its value to know whether the exit button is pressed or not.

    // Defining display function which will display the popup when it is needed.
    public static boolean display(){
        // Defining the stage.
        Stage popUpStage = new Stage();

//------------------------POPUP-SCENE------------------------------//

        // Make gridPane and scene.
        GridPane popupPagePane = new GridPane();
        Scene popupPageScene = new Scene(popupPagePane);
        // Setting background color to the pane.
        popupPagePane.setStyle("-fx-background-color:#61B15A");

        // Defining congratulations label object.
        Label congratulations = new Label("Congratulations");
        // Styling congratulations.
        congratulations.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;" );
        // Adding margin to congratulations.
        GridPane.setMargin(congratulations, new Insets(20, 20, 20, 20));
        // Adding congratulations to the pane.
        popupPagePane.add(congratulations,0,0);
        // Setting the alignment of congratulations to the center.
        GridPane.setHalignment(congratulations, HPos.CENTER);

        // Defining playAgain and Exit buttons.
        Button playAgain = new Button("Play Again");
        Button popExit = new Button("Exit");
        // Adding the buttons to the pane
        popupPagePane.add(playAgain,0,1);
        popupPagePane.add(popExit,0,2);
        // Styling the buttons using css.
        playAgain.setStyle("-fx-background-color:#FFCE89;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        popExit.setStyle("-fx-background-color:#FFF76A;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        // Changing the buttons coordinates and aligning them to the center and adding margin to them.
        playAgain.setMinHeight(45);
        playAgain.setMinWidth(80);
        popExit.setMinHeight(45);
        popExit.setMinWidth(80);
        GridPane.setHalignment(playAgain, HPos.CENTER);
        GridPane.setHalignment(popExit, HPos.CENTER);
        GridPane.setMargin(playAgain, new Insets(0, 20, 0, 20));
        GridPane.setMargin(popExit, new Insets(10, 20, 20, 20));
        // Adding action to the buttons.
        popExit.setOnAction(e -> {
            popUpStage.close();
            isExit = true;
        });
        playAgain.setOnAction(e -> {
            popUpStage.close();
            isRestart = true;
        } );
        // Changing the pane background color.
        popupPageScene.setFill(Color.GREEN);
//------------------------STAGE-SETTING------------------------------//

        // the use of initModality method and showAndWait  is to make shore that the game page is not pressable while the popup page is working.
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Winner"); // Adding title to the stage.
        popUpStage.setScene(popupPageScene); // Adding scene to the stage.
        popUpStage.showAndWait();

        // Returning isRestart value to indicate if playAgain button is pressed or not.
        return isRestart;
    }
    // a method to get isExit value.
    public static boolean getIsExit() {
        return isExit;
    }
    // a method to change isRestart value.
    public static void changeIsRestart() {
        isRestart = false;
    }
    // a method to change isExit value
    public static void changeIsExit() {
        isExit = false;
    }
}

